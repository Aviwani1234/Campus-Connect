import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.campus_connect_app.R
import com.example.campus_connect_app.model.Answer

class AnswerAdapter(
    private var answers: List<Answer>,
    private val onItemClick: (Answer) -> Unit
) : RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>() {

    inner class AnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val answerText: TextView = itemView.findViewById(R.id.answerText)

        fun bind(answer: Answer) {
            answerText.text = answer.text
            itemView.setOnClickListener {
                Log.d("AnswerAdapter", "Answer clicked: ${answer.text}")
                onItemClick(answer)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_answer, parent, false)
        return AnswerViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(answers[position])
    }

    override fun getItemCount(): Int {
        return answers.size
    }

    fun setAnswers(newAnswers: List<Answer>) {
        answers = newAnswers
        notifyDataSetChanged()
    }
}