package com.example.spiice.screens.account.notes.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.spiice.InMemoryNotesList
import com.example.spiice.R
import com.example.spiice.databinding.FragmentAddNoteBinding
import com.example.spiice.entities.Note
import com.example.spiice.entities.NoteEntity
import com.example.spiice.entities.ScheduledNoteEntity
import com.example.spiice.navigator
import com.example.spiice.utils.convertDataFromLocalDataToString
import com.example.spiice.utils.convertDataFromLongToString
import com.example.spiice.utils.convertDataFromStringToLocalData
import com.example.spiice.validations.activateButton
import com.example.spiice.validations.emptyFieldValidation
import com.example.spiice.validations.fieldHandler
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.LocalDate

const val TAG = "tag"

class AddNoteFragment : Fragment() {

    private var binding: FragmentAddNoteBinding? = null

    private var isTitleValid = false
    private var isMessageValid = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.addNoteScreenToolbar?.run {
            this.setNavigationOnClickListener {
                navigator().goBack()
            }
        }

        binding?.noteStartDataAddScreen?.text = convertDataFromLocalDataToString(LocalDate.now())

        val constraintsBuilder =
            CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.now())

        binding?.run {
            addNoteScreen.setOnClickListener {
                if (it.hasFocus()) {
                    noteTitleLayoutAddScreen.clearFocus()
                    noteDescriptionLayoutAddScreen.clearFocus()
                }
            }
        }

        binding?.run {
            noteTitleAddScreen.doAfterTextChanged {
                isTitleValid = fieldHandler(
                    noteTitleAddScreen,
                    noteTitleLayoutAddScreen,
                    emptyFieldValidation(it.toString())
                )
                addNoteButton.isEnabled = activateButton(isTitleValid, isMessageValid)
            }
        }

        binding?.run {
            noteDescriptionAddScreen.doAfterTextChanged {
                isMessageValid = fieldHandler(
                    noteDescriptionAddScreen,
                    noteDescriptionLayoutAddScreen,
                    emptyFieldValidation(it.toString())
                )
                addNoteButton.isEnabled = activateButton(isTitleValid, isMessageValid)
            }
        }

        binding?.run {
            noteStartDataAddScreen.setOnClickListener {
                val datePicker =
                    MaterialDatePicker.Builder.datePicker()
                        .setTitleText(getString(R.string.data_picker_title))
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .setCalendarConstraints(constraintsBuilder.build())
                        .build()

                datePicker.addOnPositiveButtonClickListener {
                    noteStartDataAddScreen.text = convertDataFromLongToString(it)
                }
                datePicker.show(parentFragmentManager, TAG)
            }
        }

        binding?.run {
            checkBoxAddScreen.setOnClickListener {
                if (checkBoxAddScreen.isChecked) {
                    noteStartDataAddScreen.visibility = View.VISIBLE
                } else noteStartDataAddScreen.visibility = View.GONE
            }
        }

        binding?.run {
            var newNote: Note
            addNoteButton.setOnClickListener {
                newNote = if (checkBoxAddScreen.isChecked) {
                    ScheduledNoteEntity(
                        title = noteTitleAddScreen.text.toString(),
                        addedData = LocalDate.now(),
                        scheduledData = convertDataFromStringToLocalData(noteStartDataAddScreen.text.toString()),
                        message = noteDescriptionAddScreen.text.toString(),
                    )

                } else {
                    NoteEntity(
                        title = noteTitleAddScreen.text.toString(),
                        addedData = LocalDate.now(),
                        message = noteDescriptionAddScreen.text.toString(),
                    )
                }
                InMemoryNotesList.setNotes(newNote)
                navigator().goBack()
            }
        }
    }
}