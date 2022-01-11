package com.smality.tabscompose

import androidx.compose.runtime.Composable

typealias ComposableFun = @Composable () -> Unit
sealed class TabItem(var icon: Int, var title: String, var screen: ComposableFun) {
    //Parametrede icon ve tab ismini tanımladık
    object Music : TabItem(R.drawable.ic_music, "Music", {
        //Music sekmesine tıklandığında hangi compose fonksiyonundaki ekran göstereleceğini belirtiyoruz
        MusicScreen()
    })
    object Movies : TabItem(R.drawable.ic_movie, "Movies", {
        //Movies sekmesine tıklandığında hangi compose fonksiyonundaki ekran göstereleceğini belirtiyoruz
        MoviesScreen()
    })
    object Books : TabItem(R.drawable.ic_book, "Books", { BooksScreen() })
}