package com.santimattius.android.startup.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkerParameters
import com.santimattius.android.startup.service.CrashTrackerService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

@HiltWorker
class MyWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val crashTrackerService: CrashTrackerService,
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        crashTrackerService.log(this::class.simpleName, "doWork: running my work")
        delay(2_000L)
        return Result.success()
    }

    companion object {
        const val TAG = "MyWork"
        private const val DEFAULT_MIN_INTERVAL = 15L

        fun oneTimeWorkRequest(): OneTimeWorkRequest {
            val constrains = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            return OneTimeWorkRequestBuilder<MyWorker>()
                .setConstraints(constrains)
                .addTag("my_work_tag")
                .build()
        }

        fun periodicWorkRequest(): PeriodicWorkRequest {
            val constrains = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
            return PeriodicWorkRequestBuilder<MyWorker>(
                DEFAULT_MIN_INTERVAL,
                TimeUnit.MINUTES
            ).setConstraints(constrains)
                .build()
        }
    }

}
