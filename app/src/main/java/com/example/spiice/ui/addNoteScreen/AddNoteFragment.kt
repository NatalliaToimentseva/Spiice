package com.example.spiice.ui.addNoteScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spiice.R
import com.example.spiice.databinding.FragmentAddNoteBinding
import com.example.spiice.navigator.navigator
import com.example.spiice.utils.convertDataFromLongToString
import com.example.spiice.utils.convertDataFromStringToLocalData
import com.example.spiice.utils.activateButton
import com.example.spiice.utils.emptyFieldValidation
import com.example.spiice.utils.fieldHandler
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.LocalDate

const val TAG = "tag"

class AddNoteFragment : Fragment() {

    private var binding: FragmentAddNoteBinding? = null
    private val viewModel: AddNoteViewModel by viewModels()

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
        viewModel.dataPickerVisibility.observe(viewLifecycleOwner) { visibility ->
            if (visibility) {
                binding?.noteStartDataAddScreen?.visibility = View.VISIBLE
            } else binding?.noteStartDataAddScreen?.visibility = View.GONE
        }
        viewModel.dataPickerData.observe(viewLifecycleOwner) {
            binding?.noteStartDataAddScreen?.text = it
        }

        binding?.addNoteScreenToolbar?.run {
            this.setNavigationOnClickListener {
                navigator().goBack()
            }
        }

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
                    viewModel.setData(convertDataFromLongToString(it))
                }
                datePicker.show(parentFragmentManager, TAG)
            }
        }

        binding?.run {
            checkBoxAddScreen.setOnClickListener {
                viewModel.setPickerVisibility(checkBoxAddScreen.isChecked)
            }
        }

        binding?.run {
            addNoteButton.setOnClickListener {
                if (checkBoxAddScreen.isChecked) {
                    viewModel.setScheduledNote(
                        title = noteTitleAddScreen.text.toString(),
                        addedData = LocalDate.now(),
                        scheduledData = convertDataFromStringToLocalData(noteStartDataAddScreen.text.toString()),
                        message = noteDescriptionAddScreen.text.toString(),
                    )
                } else {
                    viewModel.setSimpleNote(
                        title = noteTitleAddScreen.text.toString(),
                        addedData = LocalDate.now(),
                        message = noteDescriptionAddScreen.text.toString(),
                    )
                }
                navigator().goBack()
            }
        }
    }
}