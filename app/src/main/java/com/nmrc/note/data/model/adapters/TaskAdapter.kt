package com.nmrc.note.data.model.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nmrc.note.R
import com.nmrc.note.data.model.Task
import com.nmrc.note.data.model.util.task.TaskDiffUtil
import com.nmrc.note.data.model.util.task.TaskListener
import com.nmrc.note.databinding.ItemTaskBinding

class TaskAdapter(private val listener: TaskListener,
                  private val nav: NavController  ) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private var taskList: MutableList<Task> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).run {
            inflate(R.layout.item_task,parent,false)
        }

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentTask = taskList[position]

        with(holder.itemTaskBinding) {
            rootViewTask.setOnClickListener {
                  listener.onEditTask(currentTask, nav)
            }
        }

       holder.render(currentTask)
    }

    override fun getItemCount() = taskList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var itemTaskBinding = ItemTaskBinding.bind(view)

        fun render(task: Task) {
            with(itemTaskBinding) {
                tvTitleTask.text = task.title
                ivTopicTask.setImageResource(task.topic.drawable!!)
                civPriorityTask.setBackgroundResource(task.priority.drawable!!)

                if (task.autoDelete)
                    ivAutoDelete.visibility = View.VISIBLE
            }
        }
    }

    fun update(data: MutableList<Task>) {
        val diffUtil = TaskDiffUtil(taskList,data)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        taskList = data
        diffResults.dispatchUpdatesTo(this)
    }
}