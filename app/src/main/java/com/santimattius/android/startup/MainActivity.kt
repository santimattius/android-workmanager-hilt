package com.santimattius.android.startup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.santimattius.android.startup.service.CrashTrackerService
import com.santimattius.android.startup.service.TaskRepository
import com.santimattius.android.startup.ui.theme.AndroidStartupTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var crashTrackerService: CrashTrackerService

    @Inject
    lateinit var taskRepository: TaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidStartupTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(
                        title = "Android Startup with Hilt",
                        description = "Service initialized: ${crashTrackerService.isInitialized}",
                        onExecuteTask = {
                            taskRepository.runPeriodicWork()
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(
    title: String,
    description: String,
    onExecuteTask: () -> Unit = {},
) {

    val containerColor: Color = MaterialTheme.colorScheme.primary
    val titleContentColor: Color = MaterialTheme.colorScheme.onPrimary

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = containerColor,
                    titleContentColor = titleContentColor,
                    navigationIconContentColor = titleContentColor,
                    actionIconContentColor = titleContentColor,
                ),
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hello $title!",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Button(
                    modifier = Modifier.padding(top = 10.dp),
                    onClick = onExecuteTask
                ) {
                    Text(text = "Execute Onetime Work")
                }

                Button(
                    modifier = Modifier.padding(top = 4.dp),
                    onClick = onExecuteTask
                ) {
                    Text(text = "Execute Periodic Work")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidStartupTheme {
        Greeting("Android", "")
    }
}