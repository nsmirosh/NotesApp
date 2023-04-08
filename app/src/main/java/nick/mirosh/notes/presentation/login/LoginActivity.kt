package nick.mirosh.notes.presentation.login

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import nick.mirosh.notes.presentation.theme.MyApplicationTheme
import nick.mirosh.notes.data.di.MyApplication
import retrofit2.Retrofit
import javax.inject.Inject

class LoginActivity : ComponentActivity()  {

    @Inject
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.loginComponent().create().inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    LoginScreen()
                }
            }
        }
    }


    @Composable
    fun LoginScreen() {
        Log.d("LoginActivity", "retrofit instance = : ${retrofit.hashCode()}")
    }
}