import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.campus_connect_app.R
import com.example.campus_connect_app.model.Answer
import com.example.campus_connect_app.model.Question

class QuestionAnsAdapter(
    private val questions: List<Question>,
    private val onItemClick: (Question, Answer) -> Unit
) : RecyclerView.Adapter<QuestionAnsAdapter.QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_question, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = questions[position]
        holder.bind(question)
    }

    override fun getItemCount(): Int = questions.size

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val questionText: TextView = itemView.findViewById(R.id.questionText)
        private val answersRecyclerView: RecyclerView = itemView.findViewById(R.id.answersRecyclerView)

        fun bind(question: Question) {
            questionText.text = question.text
            answersRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            val answerAdapter = AnswerAdapter(question.answers) { answer ->
                onItemClick(question, answer)
            }
            answersRecyclerView.adapter = answerAdapter
        }
    }
}