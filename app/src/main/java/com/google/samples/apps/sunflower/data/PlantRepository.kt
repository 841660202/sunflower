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

import javax.inject.Inject
import javax.inject.Singleton

/**
 * 用于处理数据操作的存储库模块。
 * Repository module for handling data operations.
 *
 * Collecting from the Flows in [PlantDao] is main-safe.  Room supports Coroutines and moves the
 * query execution off of the main thread.
 * 从[PlantDao]中的流中收集是主要安全的。Room支持协同路由并将查询执行移出主线程。
 */
@Singleton
class PlantRepository @Inject constructor(private val plantDao: PlantDao) {
    // 获取植物列表
    fun getPlants() = plantDao.getPlants()
    // 获取单个植物
    fun getPlant(plantId: String) = plantDao.getPlant(plantId)
    // 获取植物生长区编号，入参：生长区号
    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int) = plantDao.getPlantsWithGrowZoneNumber(growZoneNumber)
}
