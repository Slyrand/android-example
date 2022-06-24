package com.slyrand.mvvmapp.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.slyrand.mvvmapp.R
import com.slyrand.mvvmapp.core.extensions.applyTint
import com.slyrand.mvvmapp.core.extensions.color
import com.slyrand.mvvmapp.databinding.ItemUserAttrBinding

class UserItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private var _binding: ItemUserAttrBinding = ItemUserAttrBinding
        .inflate(LayoutInflater.from(context), this, true)

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.UserItemView)

            val icon = typedArray.getResourceId(R.styleable.UserItemView_optionIcon, 0)
            val iconTint = typedArray.getResourceId(R.styleable.UserItemView_optionIconTint, 0)
            val text = typedArray.getString(R.styleable.UserItemView_optionText)
            val textColor = typedArray.getResourceId(R.styleable.UserItemView_optionTextColor, 0)

            if (iconTint != 0) _binding.attributeIcon.applyTint(iconTint)
            _binding.attributeIcon.setImageResource(icon)

            if (textColor != 0) _binding.attributeText.setTextColor(color(textColor))
            _binding.attributeText.text = text

            typedArray.recycle()
        }
    }

    fun setText(text: String) {
        _binding.attributeText.text = text
    }
}