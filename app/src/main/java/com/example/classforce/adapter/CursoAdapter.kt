package com.example.classforce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.classforce.R
import com.example.classforce.model.CursoModel

class CursoAdapter : RecyclerView.Adapter<CursoAdapter.CursoViewHolder>() {
    private var cursosList: List<CursoModel> = listOf()

    inner class CursoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseTitleTextView: TextView = itemView.findViewById(R.id.courseTitle)
        val courseLocalTextView: TextView = itemView.findViewById(R.id.courselocal)
        val courseDataTextView: TextView = itemView.findViewById(R.id.dataInicioTextView)

        fun bind(curso: CursoModel) {
            courseTitleTextView.text = curso.nome
            courseLocalTextView.text = curso.local
            courseDataTextView.text = curso.dataInicio

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_curso, parent, false)
        return CursoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {
        val curso = cursosList[position]
        holder.bind(curso)
    }

    override fun getItemCount(): Int = cursosList.size

    fun updateData(newData: List<CursoModel>) {
        cursosList = newData
        notifyDataSetChanged()
    }
}

