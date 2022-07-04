package com.shubham.okhttpissues.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shubham.okhttpissues.R
import com.shubham.okhttpissues.data.entity.ListOfIssues
import com.shubham.okhttpissues.databinding.ItemIssueDetailsBinding

class IssueListAdapter(
    private val mListOfIssues: List<ListOfIssues>
) : RecyclerView.Adapter<IssueListAdapter.IssueListViewHolder>() {

    inner class IssueListViewHolder(
        val itemIssueDetailsBinding: ItemIssueDetailsBinding
    ) : RecyclerView.ViewHolder(itemIssueDetailsBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueListViewHolder =
        IssueListViewHolder(
            DataBindingUtil.inflate<ItemIssueDetailsBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_issue_details,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: IssueListViewHolder, position: Int) {
        holder.itemIssueDetailsBinding.model = mListOfIssues[position]
    }

    override fun getItemCount(): Int {
        return mListOfIssues.size
    }

}