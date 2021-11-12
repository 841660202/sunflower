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

package com.google.samples.apps.sunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.google.samples.apps.sunflower.adapters.PlantAdapter
import com.google.samples.apps.sunflower.databinding.FragmentPlantListBinding
import com.google.samples.apps.sunflower.viewmodels.PlantListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantListFragment : Fragment() {

    private val viewModel: PlantListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val binding = FragmentPlantListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = PlantAdapter()
        binding.plantList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }
    // 创建选择菜单
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_plant_list, menu)
    }
    // 监听菜单选择
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_zone -> {
                updateData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUi(adapter: PlantAdapter) {
        viewModel.plants.observe(viewLifecycleOwner) { plants ->
            adapter.submitList(plants)
        }
    }

    private fun updateData() {
        // https://www.jianshu.com/p/272372acc00c
        // with函数中的参数是一个对象，我们可以带方法中直接引用对象的公有属性或者公有方法，而不用使用方法名。
//        fun setGrowZoneNumber(num: Int) {
//            growZone.value = num
//        }
//
//        fun clearGrowZoneNumber() {
//            growZone.value = PlantListViewModel.NO_GROW_ZONE
//        }
//
//        fun isFiltered() = growZone.value != PlantListViewModel.NO_GROW_ZONE
        with(viewModel) {
            if (isFiltered()) { // this.isFiltered()
                clearGrowZoneNumber() // this.clearGrowZoneNumber()
            } else {
                setGrowZoneNumber(9) // this.setGrowZoneNumber()
            }
        }
    }
}
