package com.chslcompany.desafioserasa.core.util

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.chslcompany.desafioserasa.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ConfirmDialog: DialogFragment() {
    private var yesListener: (() -> Unit)? = null
    fun setYesListener(listener: () -> Unit) { yesListener = listener }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle(getString(R.string.favorites))
            .setMessage(getString(R.string.confirm_character_exclude))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.yes)){ _, _ ->
                yesListener?.let { yes ->
                    yes()
                }
            }
            .setNegativeButton(getString(R.string.no)) { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .create()
}