package com.gabutmen.ingetwoy.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.gabutmen.ingetwoy.data.model.ReminderUnit
import com.gabutmen.ingetwoy.data.repository.ProductRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first
import java.time.LocalDate

@HiltWorker
class OffsetReminderWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val productRepository: ProductRepository
    ) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val products = productRepository.getAllProducts().first()

        products.forEach { product ->
            product.reminders.forEach { offset ->
                val referenceDate = when(offset.unit) {
                    ReminderUnit.DAY -> product.expirationDate.minusDays(offset.value.toLong())
                    ReminderUnit.MONTH -> product.expirationDate.minusMonths(offset.value.toLong())
                    ReminderUnit.YEAR -> product.expirationDate.minusYears(offset.value.toLong())
                }

                if(referenceDate == LocalDate.now()) {
//                    TBA
                    Log.d("OffsetReminderWorker", "${product.name} is going to expire in ${offset.value} ${offset.unit}(s)")
                }
            }
        }

        return Result.success()
    }

}