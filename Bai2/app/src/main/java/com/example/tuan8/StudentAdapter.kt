package com.example.tuan8
// StudentAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private var studentList: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var filteredList = studentList.toMutableList()

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tvName)
        val idTextView: TextView = itemView.findViewById(R.id.tvStudentId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = filteredList[position]
        holder.nameTextView.text = student.name
        holder.idTextView.text = student.studentId
    }

    override fun getItemCount(): Int = filteredList.size

    fun filter(query: String) {
        filteredList = if (query.length > 2) {
            studentList.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.studentId.contains(query, ignoreCase = true)
            }.toMutableList()
        } else {
            studentList.toMutableList()
        }
        notifyDataSetChanged()
    }
}
