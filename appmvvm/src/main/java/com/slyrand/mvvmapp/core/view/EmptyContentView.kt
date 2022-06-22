package com.slyrand.mvvmapp.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.slyrand.mvvmapp.R
import com.slyrand.mvvmapp.databinding.ViewEmptyContentBinding

class EmptyContentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var _binding: ViewEmptyContentBinding = ViewEmptyContentBinding
        .inflate(LayoutInflater.from(context), this, true)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.view_empty_content, this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.EmptyContentView)

            val title = typedArray.getString(R.styleable.EmptyContentView_emptyContentTitle)
            val subtitle = typedArray.getString(R.styleable.EmptyContentView_emptyContentSubtitle)

            setTitle(title ?: "")
            setSubtitle(subtitle ?: "")

            typedArray.recycle()
        }
    }

    fun setTitle(title: String) {
        _binding.emptyContentTitle.text = title
    }

    fun setSubtitle(subtitle: String) {
        _binding.emptyContentSubtitle.text = subtitle
    }
}