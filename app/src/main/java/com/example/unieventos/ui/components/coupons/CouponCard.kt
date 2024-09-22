package com.example.unieventos.ui.components.coupons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.unieventos.models.Coupon

/**
 * CouponCard is a composable that represents a card with a coupon image, name, code, discount, and expiration date.
 * @param coupon: Coupon object that contains the coupon information.ç
 * @param onClick: Function that will be called when the card is clicked.
 */
@Composable
fun CouponCard(
    coupon: Coupon,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F1F8)),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            val model = ImageRequest.Builder(LocalContext.current)
                .data(coupon.imageUrl)
                .crossfade(true)
                .build()

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                model = model,
                contentDescription = "Coupon image",
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = coupon.name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = coupon.code)
                Text(text = coupon.discount.toString() + "%")
                Text(text = "Válido hasta " + coupon.expirationDate)
            }
        }
    }
}