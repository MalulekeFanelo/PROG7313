package com.example.wonderwallet

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChatbotActivity : AppCompatActivity() {

    private lateinit var txtChatMessages: TextView
    private lateinit var edtUserQuestion: EditText
    private lateinit var btnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatbot)

        txtChatMessages = findViewById(R.id.txtChatMessages)
        edtUserQuestion = findViewById(R.id.edtUserQuestion)
        btnSend = findViewById(R.id.btnSend)

        btnSend.setOnClickListener {
            val question = edtUserQuestion.text.toString().trim()

            if (question.isEmpty()) {
                Toast.makeText(this, "Please type a question", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val answer = getBotAnswer(question)

            txtChatMessages.append("\n\nYou: $question")
            txtChatMessages.append("\nWallet Bot: $answer")

            edtUserQuestion.text.clear()
        }
    }

    private fun getBotAnswer(question: String): String {
        val q = question.lowercase()

        return when {
            q.contains("hello") || q.contains("hi") ->
                "Hi there! I am Wallet Bot. I can help you understand budgeting, saving, expenses, categories, and goals."

            q.contains("budget") ->
                "A budget is a plan for your money. In WonderWallet, you can set minimum and maximum monthly goals so you know when you are spending safely."

            q.contains("save") || q.contains("saving") ->
                "A good saving habit is to pay yourself first. Try saving a small amount before spending on wants."

            q.contains("expense") || q.contains("spending") ->
                "Expenses are the money you spend. Record each expense with an amount, category, date, and description so your reports stay accurate."

            q.contains("category") || q.contains("categories") ->
                "Categories help organise your spending. Examples include food, transport, school, entertainment, rent, and personal care."

            q.contains("goal") || q.contains("minimum") || q.contains("maximum") ->
                "Your minimum goal shows the least amount you expect to use, while your maximum goal helps prevent overspending."

            q.contains("overspend") || q.contains("over budget") ->
                "If you overspend, check which category caused it, reduce non-essential purchases, and adjust your next budget earlier."

            q.contains("student") ->
                "As a student, try separating needs from wants. Prioritise transport, food, school costs, and savings before entertainment."

            q.contains("receipt") || q.contains("photo") ->
                "Receipt photos help you keep proof of expenses. They are useful when checking what you bought later."

            q.contains("help") ->
                "You can ask me things like: How do I save money? What is a budget? How do categories work? What happens if I overspend?"

            else ->
                "I am not sure yet, but I can help with budgeting, saving, expenses, categories, goals, receipts, and student spending habits."
        }
    }
}