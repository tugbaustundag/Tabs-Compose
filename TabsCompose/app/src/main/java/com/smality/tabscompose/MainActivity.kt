package com.smality.tabscompose
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}
//Ana ekranda yapılacaklar için MainScreen fonksiyonu oluşturalım
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun MainScreen() {
    //TabItem dosyasında belirlediğim tab öğelerini diziye yerleşitiriyoruz
    val tabs = listOf(TabItem.Music, TabItem.Movies, TabItem.Books)
    val pagerState = rememberPagerState()
    //Scaffold yerleşim yapısına TopBarfonksiyonunu çağırarak topbar ekleyelim.
    Scaffold(
        topBar = { TopBar() },
    ) {
        Column {
            //Tabs fonksiyonunu çağırarak tab ları görünür yaptım.
            Tabs(tabs = tabs, pagerState = pagerState)
            //TabsContent fonksiyonunu çağırarak ilgili ekran içeriklerini gösterdim
            TabsContent(tabs = tabs, pagerState = pagerState)
        }
    }
}

//MainScreen fonksiyonunu çağırarak yapılan arayüz kodlarımızı
//önizleme yapıyoruz
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
//Bar bölümündeki başlığı, rengini ve font boyutunu tanımlıyoruz
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text("Tabs Jetpack Compose", fontSize = 18.sp) },
        contentColor = Color.White
    )
}
//TopBar fonksiyonunda yapılan arayüz kodlarımızı
//önizleme yapıyoruz
@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}


@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        //Tab başlıklarının rengini belirliyoruz
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }) {
        //tabs dizisinden gelen icon, başlık bilgilerinin Tabs görünümüne aktarılması
        tabs.forEachIndexed { index, tab ->
            LeadingIconTab(
                icon = { Icon(painter = painterResource(id = tab.icon), contentDescription = "") },
                text = { Text(tab.title) },
                selected = pagerState.currentPage == index,
                //Tab öğelerine tıklandığında ilgili tab ekranının gösterilmesi
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun TabsPreview() {
    val tabs = listOf(
        TabItem.Music,
        TabItem.Movies,
        TabItem.Books
    )
    val pagerState = rememberPagerState()
    Tabs(tabs = tabs, pagerState = pagerState)
}
//Seçilen Tab öğesinin ilgili ekranındaki içeriği gösteren fonksiyon
@ExperimentalPagerApi
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}

//TabsContent fonksiyonunu önizleme
@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun TabsContentPreview() {
    val tabs = listOf(
        TabItem.Music,
        TabItem.Movies,
        TabItem.Books
    )
    val pagerState = rememberPagerState()
    TabsContent(tabs = tabs, pagerState = pagerState)
}
