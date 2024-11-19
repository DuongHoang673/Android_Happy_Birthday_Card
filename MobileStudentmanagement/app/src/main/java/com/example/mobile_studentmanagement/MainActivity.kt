package com.example.mobile_studentmanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private val students = mutableListOf(
        StudentModel("Nguyễn Văn An", "SV021"),
        StudentModel("Trần Thị Bình", "SV022"),
        StudentModel("Phạm Văn Cường", "SV023"),
        StudentModel("Lê Thị Dung", "SV024"),
        StudentModel("Hoàng Văn Đạt", "SV025"),
        StudentModel("Vũ Thị Hà", "SV026"),
        StudentModel("Đỗ Văn Khoa", "SV027"),
        StudentModel("Bùi Thị Lan", "SV028"),
        StudentModel("Nguyễn Văn Minh", "SV029"),
        StudentModel("Phạm Thị Nga", "SV030"),
        StudentModel("Trần Văn Sơn", "SV031"),
        StudentModel("Lê Thị Thảo", "SV032"),
        StudentModel("Vũ Văn Tùng", "SV033"),
        StudentModel("Đinh Thị Uyên", "SV034"),
        StudentModel("Nguyễn Văn Việt", "SV035")    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentAdapter = StudentAdapter(students, this)

        findViewById<RecyclerView>(R.id.recycler_view_students).run {
            adapter = studentAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        findViewById<Button>(R.id.btn_add_new).setOnClickListener {
            showAddStudentDialog()
        }
    }

    fun showAddStudentDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_student, null)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Add New Student")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val name = dialogView.findViewById<EditText>(R.id.edit_text_name).text.toString()
                val id = dialogView.findViewById<EditText>(R.id.edit_text_id).text.toString()
                val newStudent = StudentModel(name, id)
                students.add(newStudent)
                studentAdapter.notifyItemInserted(students.size - 1)
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }

    fun showEditStudentDialog(position: Int) {
        val student = students[position]
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_student, null).apply {
            findViewById<EditText>(R.id.edit_text_name).setText(student.studentName)
            findViewById<EditText>(R.id.edit_text_id).setText(student.studentId)
        }
        val dialog = AlertDialog.Builder(this)
            .setTitle("Edit Student")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                val name = dialogView.findViewById<EditText>(R.id.edit_text_name).text.toString()
                val id = dialogView.findViewById<EditText>(R.id.edit_text_id).text.toString()
                student.studentName = name
                student.studentId = id
                studentAdapter.notifyItemChanged(position)
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }

    fun showDeleteStudentDialog(position: Int) {
        val student = students[position]
        val dialog = AlertDialog.Builder(this)
            .setTitle("Delete Student")
            .setMessage("Are you sure you want to delete ${student.studentName}?")
            .setPositiveButton("Delete") { _, _ ->
                students.removeAt(position)
                studentAdapter.notifyItemRemoved(position)
                Snackbar.make(findViewById(R.id.recycler_view_students), "${student.studentName} deleted", Snackbar.LENGTH_LONG)
                    .setAction("Undo") {
                        students.add(position, student)
                        studentAdapter.notifyItemInserted(position)
                    }.show()
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }
}