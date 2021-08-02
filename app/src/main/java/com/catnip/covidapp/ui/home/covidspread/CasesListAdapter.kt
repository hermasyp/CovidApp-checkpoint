package com.catnip.covidapp.ui.home.covidspread;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.catnip.covidapp.data.network.entity.responses.kawalcorona.TotalCaseProvinceResponse
import com.catnip.covidapp.databinding.ItemCasesListBinding

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class CasesListAdapter(val itemClick: (TotalCaseProvinceResponse) -> Unit) :
    RecyclerView.Adapter<CasesListAdapter.CasesListViewHolder>() {

    var items: List<TotalCaseProvinceResponse> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasesListViewHolder {
        val binding =
            ItemCasesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CasesListViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: CasesListViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size


    class CasesListViewHolder(
        private val binding: ItemCasesListBinding,
        val itemClick: (TotalCaseProvinceResponse) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: TotalCaseProvinceResponse) {
            with(item) {
                binding.apply{
                    tvProvinceName.text = attributes?.provinsi
                    tvProvinceDeathCase.text = attributes?.kasusMeni.toString()
                    tvProvincePositiveCase.text = attributes?.kasusPosi.toString()
                    tvProvinceRecoveredCase.text = attributes?.kasusSemb.toString()
                }
                itemView.setOnClickListener { itemClick(this) }
            }

        }
    }

}