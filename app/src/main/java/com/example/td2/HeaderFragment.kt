package com.example.td2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.td2.network.Api
import kotlinx.android.synthetic.main.header_fragment.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class HeaderFragment :Fragment()
{
    private val coroutineScope = MainScope()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.header_fragment,container)
    }

    override fun onResume() {
        super.onResume()
        coroutineScope.launch {
            val userinfo = Api.userService.getInfo().body()
            user_name.text= userinfo?.firstname ?: ""

        }
        Glide.with(this).load("https://goo.gl/gEgYUd").into(user_avatar)

    }

    override fun onDestroy() {

        coroutineScope.cancel()
        super.onDestroy()
    }
}