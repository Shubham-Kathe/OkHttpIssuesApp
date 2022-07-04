package com.shubham.okhttpissues.data.entity
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.SerializedName
import com.shubham.okhttpissues.R
import com.shubham.okhttpissues.utils.GlideApp

@Entity
data class ListOfIssues(
    @PrimaryKey
    @SerializedName("id") var id: Int,
    @SerializedName("title") var issueTitle: String? = null,
    @SerializedName("userName") var userName: String? = null,
    @SerializedName("userAvatar") var avatar: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("lastUpdate") var lastUpdate: String? = null,
)


@BindingAdapter("userAvatar")
fun loadPhoto(view: ImageView, imageUrl: String?) {
    GlideApp.with(view.getContext())
        .load(imageUrl).apply(RequestOptions().placeholder((R.drawable.ic_launcher_foreground)))
        .into(view)
}