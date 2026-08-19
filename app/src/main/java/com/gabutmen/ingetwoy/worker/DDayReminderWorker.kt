package com.gabutmen.ingetwoy.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.gabutmen.ingetwoy.data.repository.ProductRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first
import java.time.LocalDate

@HiltWorker
class DDayReminderWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val productRepository: ProductRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val products = productRepository.getAllProducts().first()

        products.filter { product ->
            product.expirationDate == LocalDate.now()
        }.forEach { product ->
//                TBA
            Log.d("DDayReminderWorker", "${product.name} is expiring today!!")
        }

        return Result.success()
    }

}