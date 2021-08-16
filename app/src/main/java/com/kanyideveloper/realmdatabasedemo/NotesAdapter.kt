package com.kanyideveloper.realmdatabasedemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kanyideveloper.realmdatabasedemo.databinding.NoteRowBinding

class NotesAdapter(private val onClickListener: OnClickListener, private val onSwipe: OnSwiper) :
    ListAdapter<Note, NotesAdapter.MyViewHolder>(MyDiffUtil) {

    object MyDiffUtil : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class MyViewHolder(private val binding: NoteRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note?) {
            binding.titleTextView.text = note?.title
            binding.descriptionTextView.text = note?.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            NoteRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)

        onSwipe.onClick(note)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(note)
        }
    }

    class OnClickListener(val clickListener: (note: Note) -> Unit) {
        fun onClick(note: Note) = clickListener(note)
    }

    class OnSwiper(val clickListener: (note: Note) -> Unit) {
        fun onClick(note: Note) = clickListener(note)
    }
}