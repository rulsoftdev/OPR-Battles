package dev.rulsoft.oprbattles.repository

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dev.rulsoft.oprbattles.data.Club
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val TAG = "ClubRepository"

class ClubRepository
@Inject constructor(
    private val db: FirebaseFirestore
) {

    suspend fun fetchClubs(): List<Club> = withContext(Dispatchers.IO) {

        return@withContext try {
            db.collection("Clubs")
                .get()
                .await()
                .documents
                .mapNotNull {document ->
                    document.toObject(Club::class.java)
                }
        } catch (e: Exception) {
            Log.w(TAG, "Error fetching clubs: ${e.message}", e)
            emptyList()
        }
    }

    suspend fun fetchClubById(uid: String): Club? = withContext(Dispatchers.IO) {
        val db = Firebase.firestore
        return@withContext try {
            db.collection("Clubs")
                .document(uid)
                .get()
                .await()
                .toObject(Club::class.java)
        } catch (e: Exception) {
            Log.w(TAG, "Error fetching club with ID $uid: ${e.message}", e)
            null
        }
    }

}