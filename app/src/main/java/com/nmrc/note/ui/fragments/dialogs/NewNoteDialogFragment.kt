package com.nmrc.note.ui.fragments.dialogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.nmrc.note.R
import com.nmrc.note.databinding.FragmentNewNoteDialogBinding


class NewNoteDialogFragment : Fragment() {

    private lateinit var binding: FragmentNewNoteDialogBinding

    private var clicked = false

    private val rotateOpenAnimation: Animation by lazy { AnimationUtils.loadAnimation(context,R.anim.rotate_open_anim) }
    private val rotateCloseAnimation: Animation by lazy { AnimationUtils.loadAnimation(context,R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(context,R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(context,R.anim.to_bottom_anim) }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentNewNoteDialogBinding.inflate(layoutInflater,container,false)

        with(binding){

            fabAddInfoNote.setOnClickListener {

                onAddButtomInfoClicked()
            }
        }

        return inflater.inflate(R.layout.fragment_new_note_dialog, container, false)
    }

    private fun onAddButtomInfoClicked() {

        setVisibility(clicked)
        setAnimation(clicked)

        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean) {

        if(!clicked) with(binding) {
            fabAddInfoNoteImage.startAnimation(fromBottom)
            fabAddInfoNote.startAnimation(rotateOpenAnimation)
        }

        else with(binding) {
            fabAddInfoNoteImage.startAnimation(toBottom)
            fabAddInfoNote.startAnimation(rotateCloseAnimation)
        }

    }

    private fun setVisibility(clicked: Boolean) {

        if(!clicked) with(binding) { fabAddInfoNoteImage.visibility = View.VISIBLE }

        else with(binding) { fabAddInfoNoteImage.visibility = View.INVISIBLE }
    }

}