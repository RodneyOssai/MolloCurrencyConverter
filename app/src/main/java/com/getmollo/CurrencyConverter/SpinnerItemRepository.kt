import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.getmollo.CurrencyConverter.RetrofitClient
import com.getmollo.CurrencyConverter.SpinnerItem
import com.getmollo.CurrencyConverter.SpinnerItemDao

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class SpinnerItemRepository(private val spinnerDao: SpinnerItemDao) {


    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val currenciesList: LiveData<List<SpinnerItem?>?>? = spinnerDao.currenciesList

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(currency: SpinnerItem) {
        spinnerDao.insert(currency)
    }


}