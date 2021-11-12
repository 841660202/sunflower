/*
 * Copyright 2019 Google LLC
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

package com.google.samples.apps.sunflower.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.samples.apps.sunflower.GardenFragment
import com.google.samples.apps.sunflower.PlantListFragment

const val MY_GARDEN_PAGE_INDEX = 0
const val PLANT_LIST_PAGE_INDEX = 1

class SunflowerPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     * 将ViewPager页面索引映射到各自的片段
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        MY_GARDEN_PAGE_INDEX to { GardenFragment() }, // 我的花园
        PLANT_LIST_PAGE_INDEX to { PlantListFragment() } // 植物目录
    )

    /**
     * 获取fragment数量
     */
    override fun getItemCount() = tabFragmentsCreators.size // tab的数量

    /**
     * 根据位置创建Fragment
     */
    override fun createFragment(position: Int): Fragment {
        /**
         * .invoke()
         * 函数执行，因为tabFragmentsCreators[position]可能不存在，所以不能直接执行tabFragmentsCreators[position]()
         * tabFragmentsCreators[position]?.() ,因为kotlin没有这个写法，
         * 只有.invoke()等效()
         * 所以tabFragmentsCreators[position]?.invoke()
         * */
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}
