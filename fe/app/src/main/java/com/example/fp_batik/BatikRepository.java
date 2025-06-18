package com.example.fp_batik;

public class BatikRepository {
}
class BatikRepository(private val apiService: BatikApiService) {
    private val cache = mutableMapOf<String, Batik>()

    suspend fun getBatikById(id: String): Batik {
        return cache[id] ?: apiService.getBatikDetail(id).also {
            cache[id] = it
        }
    }
}