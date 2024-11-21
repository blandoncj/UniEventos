import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.descriptors.StructureKind

@Composable
fun ImagePicker(
    label: String,
    imageUri: Uri?,
    onImagePicked: (Uri) -> Unit
) {
    val config = mapOf(
        "cloud_name" to "dmqwpzhd4",
        "api_key" to "119262622873127",
        "api_secret" to "hbxPLOgkp08Knz-KJAY5DA8I4n8"
    )
    val context = LocalContext.current

    val cloudinary = Cloudinary(config)
    val scope = rememberCoroutineScope()
    var selectedImageUri by remember { mutableStateOf<Uri?>(imageUri) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            scope.launch(Dispatchers.IO) {

                val inputStream = context.contentResolver.openInputStream(uri)
                inputStream?.use { stream ->
                    val result = cloudinary.uploader().upload(stream, ObjectUtils.emptyMap())
                        selectedImageUri = Uri.parse(result["secure_url"].toString())
                        onImagePicked(Uri.parse(result["secure_url"].toString()))
                }
            }

        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        Button(
            onClick = {
                val permissionCheckResult =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        ContextCompat.checkSelfPermission(
                            context,
                            android.Manifest.permission.READ_MEDIA_IMAGES
                        )
                    } else {
                        ContextCompat.checkSelfPermission(
                            context,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                    }

                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                    launcher.launch("image/*")
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
                    } else {
                        permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    }
                }
                //launcher.launch("image/*")

            }) {
            Text(text = label)
        }

        Spacer(modifier = Modifier.height(16.dp))

        selectedImageUri?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = "Vista previa de la imagen",
                modifier = Modifier
                    .size(200.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Crop
            )
        } ?: Text(
            text = "No hay imagen seleccionada",
            color = Color.Gray
        )
    }
}
