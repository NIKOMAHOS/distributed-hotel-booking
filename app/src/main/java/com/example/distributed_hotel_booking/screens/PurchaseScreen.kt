package com.example.distributed_hotel_booking.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.times
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.distributed_hotel_booking.data.Booking
import com.example.distributed_hotel_booking.data.DataProvider
import com.example.distributed_hotel_booking.viewmodel.LoginViewModel
import com.example.distributed_hotel_booking.viewmodel.SharedViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.temporal.Temporal
import java.util.Date
import java.util.Locale

@Composable
fun PaymentScreen(navController: NavController, roomId:String?, startDate: String?, endDate:String?, guests:String?){
    Log.d("INTO PAYMENT SCREEN", "PAY UP !")
    val room = DataProvider.getRoomById(roomId)
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.getDefault())
    Log.d("DATES PICKED", "${startDate} - ${endDate}")
    val checkInDate = LocalDate.parse(startDate, formatter)
    val checkOutDate = LocalDate.parse(endDate, formatter)
    Log.d("DATES PARSED", "${checkInDate} - ${checkOutDate}")
//    val roomPrice = room?.price ?: 0f
    val guestCount = guests?.toInt()

    val daysBetween = ChronoUnit.DAYS.between(checkInDate, checkOutDate)
    Log.d("PaymentScreen", "Days between: $daysBetween")
//    Log.d("PaymentScreen", "Room price per Night: $roomPrice")
//    val total = roomPrice * daysBetween
//    Log.d("PaymentScreen", "Total: $total")
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Booking Details",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Room Name: ${room?.roomName}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Check-in Date: $checkInDate",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Check-out Date: $checkOutDate",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Number of Guests : $guests",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp))
//            Text(
//                text = "Total Amount: $total",
//                style = MaterialTheme.typography.headlineSmall,
//                modifier = Modifier.padding(16.dp))
            Button(
                onClick = {
                    // Create a new Booking object
//                    Log.d("ROOM", "${room?.roomName}")
//                    val booking = Booking("1", room!!.roomId, checkInDate.toDate(), checkOutDate.toDate(), guestCount, total!!)
//                    // Book the room
//                    bookRoom(navController, booking)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Complete Booking")
            }
        }
    }
}

fun bookRoom(navController: NavController, newBooking: Booking) {
//    // Try to book the room
//    val bookingSuccessful = DataProvider.addBooking(booking = newBooking)
//
//    if (bookingSuccessful) {
//        // If the booking was successful, show a success message and navigate to the My Bookings screen
//        Toast.makeText(navController.context, "Your booking was successful. Enjoy your stay !", Toast.LENGTH_LONG).show()
//        navController.navigate("user_bookings_screen") {
//            popUpTo("user_home_screen") { inclusive = false }
//        }
//    } else {
//        // If the booking was unsuccessful, show an error message and navigate to the RoomDetailsScreen
//        Toast.makeText(navController.context, "Booking was unsuccessful. Please try again.", Toast.LENGTH_LONG).show()
//        navController.navigate("room_details_screen/${newBooking.roomId}"){
//            popUpTo("room_details_screen/${newBooking.roomId}"){ inclusive = true }
//        }
//    }
}

fun LocalDate.toDate(): Date {
    return Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant())
}

@Preview(showBackground = true)
@Composable
fun PreviewPaymentScreen() {
    PaymentScreen(
        navController = rememberNavController(),
        roomId = "1",
        startDate = "01-01-2023",
        endDate = "10-01-2023",
        guests = "2"
    )
}