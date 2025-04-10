package com.webilovalarishlabchiqish.app.fragments

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.webilovalarishlabchiqish.app.R
import com.webilovalarishlabchiqish.app.databinding.FragmentDarslikBinding
import java.io.File


class Darslik : Fragment() {

    private lateinit var binding:FragmentDarslikBinding
    private lateinit var fragmentContext:Context

    private val resources = listOf(
        "1_dars.pdf",
        "2_dars.pdf",
        "3_dars.pdf",
        "4_dars.pdf",
        "5_dars.pdf",
        "6_dars.pdf",
        "7_dars.pdf",
        "8_dars.pdf",
        "django_ru.pdf",
        "django_uz.pdf",

    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_darslik, container, false)
        binding = FragmentDarslikBinding.bind(view)

        val buttons = listOf(
            binding.darslik1,
            binding.darslik2,
            binding.darslik3,
            binding.darslik4,
            binding.darslik5,
            binding.darslik6,
            binding.darslik7,
            binding.darslik8,
            binding.djangoru,
            binding.djangouz,
        )

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                openFile(fragmentContext, resources[index])
            }
        }

        return binding.root
    }

    private fun openFile(context: Context, fileName: String) {
        val assetManager = context.assets
        val inputStream = assetManager.open(fileName)

        val outputFile = File(context.cacheDir, fileName)
        inputStream.use { input ->
            outputFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", outputFile)

        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, if (fileName.endsWith(".pdf")) "application/pdf" else "application/msword")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "Faylni ochish uchun mos dastur topilmadi!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }

}