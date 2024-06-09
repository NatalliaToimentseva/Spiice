package com.example.spiice.notes.screen

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doAfterTextChanged
import com.example.spiice.R
import com.example.spiice.entities.NoteEntity
import com.example.spiice.validations.activateButton
import com.example.spiice.validations.emptyFieldValidation
import com.example.spiice.validations.fieldHandler
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import java.text.DateFormat
import java.util.Calendar

const val TAG = "tag"

class AddNoteActivity : AppCompatActivity() {

    private var isTitleValid = false
    private var isMessageValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val rootAddNoteCL = findViewById<ConstraintLayout>(R.id.add_note_screen)
        val actionBarAddNote =
            findViewById<androidx.appcompat.widget.Toolbar>(R.id.add_note_screen_toolbar)
        val titleAddNoteTIL = findViewById<TextInputLayout>(R.id.note_title_layout_add_screen)
        val messageAddNoteTIL =
            findViewById<TextInputLayout>(R.id.note_description_layout_add_screen)
        val startDataTextView = findViewById<TextView>(R.id.note_start_data_add_screen)
        val titleAddNoteET = findViewById<EditText>(R.id.note_title_add_screen)
        val messageAddNoteET = findViewById<EditText>(R.id.note_description_add_screen)
        val addNoteButton = findViewById<Button>(R.id.add_note_button)

        val dataFormat =
            DateFormat.getDateInstance(DateFormat.DEFAULT).format(Calendar.getInstance().time)

        setSupportActionBar(actionBarAddNote)
        actionBarAddNote.setNavigationOnClickListener {
            finish()
        }

        startDataTextView.text = dataFormat

        val constraintsBuilder =
            CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.now())

        rootAddNoteCL.setOnClickListener {
            if (it.hasFocus()) {
                titleAddNoteTIL.clearFocus()
                messageAddNoteTIL.clearFocus()
            }
        }

        titleAddNoteET.doAfterTextChanged {
            isTitleValid = fieldHandler(
                titleAddNoteET,
                titleAddNoteTIL,
                emptyFieldValidation(it.toString())
            )
            addNoteButton.isEnabled = activateButton(isTitleValid, isMessageValid)
        }

        messageAddNoteET.doAfterTextChanged {
            isMessageValid = fieldHandler(
                messageAddNoteET,
                messageAddNoteTIL,
                emptyFieldValidation(it.toString())
            )
            addNoteButton.isEnabled = activateButton(isTitleValid, isMessageValid)
        }

        startDataTextView.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText(getString(R.string.data_picker_title))
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .setCalendarConstraints(constraintsBuilder.build())
                    .build()

            datePicker.addOnPositiveButtonClickListener {
                startDataTextView.text = DateFormat.getDateInstance(DateFormat.DEFAULT).format(it)
            }
            datePicker.show(supportFragmentManager, TAG)
        }

        addNoteButton.setOnClickListener {
            val newNote = NoteEntity(
                title = titleAddNoteET.text.toString(),
                startingData = startDataTextView.text.toString(),
                message = messageAddNoteET.text.toString(),
            )
            intent.putExtra(KEY, newNote)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}