/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the Plant class.
 */
@Dao
interface PlantDao {
    /**从数据中获取 植物，以名字分组*/
    @Query("SELECT * FROM plants ORDER BY name")
    fun getPlants(): Flow<List<Plant>>
    /**从数据中获取 生长区域为 growZoneNumber 植物，以名字分组 */
    @Query("SELECT * FROM plants WHERE growZoneNumber = :growZoneNumber ORDER BY name")
    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int): Flow<List<Plant>>
    /**获取植物信息 */
    @Query("SELECT * FROM plants WHERE id = :plantId")
    fun getPlant(plantId: String): Flow<Plant>
    /**批量插入植物，冲突替换*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<Plant>)
}
