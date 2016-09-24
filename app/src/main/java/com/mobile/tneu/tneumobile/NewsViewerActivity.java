package com.mobile.tneu.tneumobile;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mobile.tneu.tneumobile.model.News;
import com.mobile.tneu.tneumobile.recyclerview.EndlessRecyclerViewScrollListener;
import com.mobile.tneu.tneumobile.recyclerview.ItemClickSupport;
import com.mobile.tneu.tneumobile.recyclerview.NewsRecyclerViewAdapter;

import java.util.ArrayList;

public class NewsViewerActivity extends AppCompatActivity {

    private BroadcastReceiver mNewsViewerActivityBroadcastReceiver;
    private NewsRecyclerViewAdapter mNewsRecyclerViewAdapter;
    private ArrayList<News> mNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void onResume() {
        super.onResume();
//        LocalBroadcastManager.getInstance(this).registerReceiver(mNewsViewerActivityBroadcastReceiver,
//                new IntentFilter("pushNotification"));
    }

    protected void onPause() {
        super.onPause();
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(mNewsViewerActivityBroadcastReceiver);

    }

    private void init() {
        //TODO: change on real data
        //region Initializing test data
        mNews = initTestNewsData();
        //endregion
        setContentView(R.layout.activity_news_viewer);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mNewsRecyclerViewAdapter = new NewsRecyclerViewAdapter(mNews);
        recyclerView.setAdapter(mNewsRecyclerViewAdapter);
        setOnItemClickListenerForRecyclerView(recyclerView);
        addOnScrollListenerToRecyclerView(recyclerView, linearLayoutManager);
//        registerBroadcastReceiver();
    }

    private void addOnScrollListenerToRecyclerView(RecyclerView recyclerView, final LinearLayoutManager linearLayoutManager) {
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                //TODO:specify and implement or remove
            }
        });
    }

    private void setOnItemClickListenerForRecyclerView(RecyclerView recyclerView) {
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        //TODO:specify and implement
                    }
                }
        );
    }

    // TODO: Remove
    //region Test Data
    ArrayList<News> initTestNewsData() {
        String br = "[{\"mTitle\":\"Студенти-першокурсники ФЕУ відвідали музей ТНЕУ\",\"mDate\":\"2016-09-21T16:24:00.000Z\",\"mTopic\":\"Новини / Студентське життя\",\"mImageLink\":\"http://www.tneu.edu.ua/uploads/posts/2016-09/1474468395_na11n6pcdk0.jpg\",\"mDescription\":\"19-20 вересня студенти факультету економіки та управління з великим задоволенням і масою позитивних вражень переглядали експозицію Музею, слухали розповідь про становлення та розвиток ТНЕУ. Екскурсію цікаво та змістовно провів директор Музею Віталій Григорович Михайлиця.\",\"mReadMoreLink\":\"http://www.tneu.edu.ua/news/9704-studenty-pershokursnyky-feu-vidvidaly-muzei-tneu.html\"},{\"mTitle\":\"У пошуках «нової енергії» в ННІМЕВ завітали представники МП «Візит»\",\"mDate\":\"2016-09-21T15:25:00.000Z\",\"mTopic\":\"Новини / Наші ініціативи\",\"mImageLink\":\"http://www.tneu.edu.ua/uploads/posts/2016-09/1474460263_logo-z-vizyt.jpg\",\"mDescription\":\"Всі молоді люди рано чи пізно стикаються з проблемою пошуку роботи. І зовсім не просто зробити це сьогодні, коли висока конкуренція на ринку праці, а ти є студентом без будь-якого досвіду роботи. Проте існують винятки із правил, коли підприємство зацікавлене тільки в студентах, які зможуть влити «нову енергію» в діяльність і розвиток підприємства. Саме з метою пошуку нових працівників на МП «Візит» 14 вересня 2016 року завітала в ННІМЕВ ім. Б.Д.Гаврилишина ТНЕУ юрисконсульт підприємства Тетяна Лук’янчук.\",\"mReadMoreLink\":\"http://www.tneu.edu.ua/news/9703-u-poshukah-novoi-energii-v-nnimev-zavitaly-predstavnyky-mp-vizyt.html\"},{\"mTitle\":\"До уваги студентів ННІМЕВ ім. Б.Д. Гаврилишина!\",\"mDate\":\"2016-09-21T12:10:00.000Z\",\"mTopic\":\"Новини / Наші ініціативи\",\"mImageLink\":\"http://www.tneu.edu.ua/uploads/posts/2016-09/1474449355_4-kjuj.png\",\"mDescription\":\"В рамках реалізації угоди про співпрацю між Тернопільським національним економічним університетом та Університетом прикладних наук INHOLLAND (Королівство Нідерланди) з метою реалізації програми ERASMUS+ відбудеться конкурсний відбір студентів на семестрове навчання. Тестування з англійської мови в рамках конкурсного відбору на семестрове навчання в Університеті прикладних наук INHOLLAND відбудеться 22 вересня 2016 року о 12.50 (ауд.15 корпус №10), співбесіда проходитиме 23 вересня 2016 року о 9.35 (ауд.15 корпус №10).\",\"mReadMoreLink\":\"http://www.tneu.edu.ua/news/9700-do-uvagy-studentiv-nnimev-im-bdgavrylyshyna.html\"},{\"mTitle\":\"Проект сприяння академічній доброчесності в Україні (SAIUP)\",\"mDate\":\"2016-09-20T16:43:00.000Z\",\"mTopic\":\"Новини\",\"mImageLink\":\"http://www.tneu.edu.ua/uploads/posts/2016-09/1474378918_img_9535.jpg\",\"mDescription\":\"Тернопільський національний економічний університет – один з десяти університетів-учасників Проекту сприяння академічній доброчесності в Україні (SAIUP), який організовується Американськими радами з міжнародної освіти спільно з Міністерством освіти та науки України за підтримки Посольства США.\",\"mReadMoreLink\":\"http://www.tneu.edu.ua/news/9698-proekt-spryiannia-akademichnii-dobrochesnosti-v-ukraini-saiup.html\"},{\"mTitle\":\"КАСТИНГ ДЕКЛАМАТОРІВ\",\"mDate\":\"2016-09-20T12:32:00.000Z\",\"mTopic\":\"Новини\",\"mImageLink\":\"http://www.tneu.edu.ua/uploads/posts/2016-09/1474364033_images-1.jpg\",\"mDescription\":\"Усіх студентів та молодих викладачів університету, які люблять українське поетичне слово, запрошуємо до участі в кастингу декламаторів. Переможці кастингу будуть залучені в якості ведучих святкових імпрез, шоу-програм, телепроектів ТНЕУ, а також запрошені до гри в університетських театралізованих дійствах.\",\"mReadMoreLink\":\"http://www.tneu.edu.ua/news/9697-kastng-deklamatoriv.html\"},{\"mTitle\":\"«МАРКЕТИНГОВІ КОМУНІКАЦІЇ ТА ЛОГІСТИКА У СФЕРІ ТЕХНОЛОГІЙ ЕНЕРГОЗБЕРЕЖЕННЯ В УКРАЇНІ ТА СВІТІ»\",\"mDate\":\"2016-09-19T16:44:00.000Z\",\"mTopic\":\"Новини\",\"mImageLink\":\"http://www.tneu.edu.ua/uploads/posts/2016-09/1474292678_conference.jpg\",\"mDescription\":\"Шановні колеги! Кафедра міжнародної економіки, маркетингу і менеджменту Івано-Франківського навчально-наукового інституту менеджменту ТНЕУ на правах співорганізатора запрошує Вас взяти участь у Міжнародній науково-практичній конференції «Маркетингові комунікації та логістика у сфері технологій енергозбереження в Україні та світі», яка відбудеться у м. Дніпро (Україна) 20-21 жовтня 2016 року.\",\"mReadMoreLink\":\"http://www.tneu.edu.ua/news/9682-marketngovi-komunikaciyi-ta-logistka-u-sferi-tehnologiy-energozberezhennya-v-ukrayini-ta-sviti.html\"},{\"mTitle\":\"ПРИЙДИ НА КАСТИНГ ВОКАЛІСТІВ!\",\"mDate\":\"2016-09-19T14:51:00.000Z\",\"mTopic\":\"Новини / Оголошення\",\"mImageLink\":\"http://www.tneu.edu.ua/uploads/posts/2016-09/1474285872_microphone-on-stage.jpg\",\"mDescription\":\"Дорогий студенте! Саме ти можеш стати зіркою найбільшої сцени ТНЕУ! Прийшов час заявити про себе! Якщо Ти володієш: а) талантом до співу та артистизмом; б) яскравими зовнішніми даними; в) великим бажанням удосконалювати свої здібності,\",\"mReadMoreLink\":\"http://www.tneu.edu.ua/news/9680-pryd-na-kastng-vokalistiv.html\"},{\"mTitle\":\"Студенти факультету фінансів відвідали підприємство \\\"Микулинецький Бровар\\\"\",\"mDate\":\"2016-09-19T12:51:00.000Z\",\"mTopic\":\"Новини\",\"mImageLink\":\"http://www.tneu.edu.ua/uploads/posts/2016-09/1474278401_img_5767.jpg\",\"mDescription\":\"16.09.2016 року студенти ІV курсу факультету фінансів Тернопільського національного економічного університету з викладачем кафедри податків і фіскальної політики Інною Анатолівною Гуцул відвідали підприємство \\\"Микулинецький Бровар\\\" - підприємство, яке є великим платником податків, і поряд з тим , єдину пивоварню на Україні, яка варить живе непастеризоване пиво, а також іншу безалкогольну продукцію, що в даний час користується на ринку неабияким попитом.\",\"mReadMoreLink\":\"http://www.tneu.edu.ua/news/9679-studenty-fakultetu-finansiv-vidvidaly-pidpryiemstvo-mykulyneckyi-brovar.html\"},{\"mTitle\":\"Школа польського та європейського права оголошує набір слухачів на 2016-2017 навчальний рік\",\"mDate\":\"2016-09-19T10:01:00.000Z\",\"mTopic\":\"Новини / Оголошення\",\"mImageLink\":\"http://www.tneu.edu.ua/uploads/posts/2016-09/1473409454_1409817617_2008_06_13_40d_4190-121.jpg\",\"mDescription\":\"Школа польського та європейського права – це спільна програма Тернопільського національного економічного університету та Ягеллонського Університету (м. Краків, Республіка Польща).\",\"mReadMoreLink\":\"http://www.tneu.edu.ua/news/9623-shkola-polskogo-ta-ievropeiskogo-prava-ogoloshuie-nabir-sluhachiv-na-2016-2017-navchalnyi-rik.html\"},{\"mTitle\":\"На кафедрі фінансів імені С.І. Юрія розпочав роботу новостворений студентський науковий гурток „Financial education”/„Фінансова просвіта”\",\"mDate\":\"2016-09-16T17:07:00.000Z\",\"mTopic\":\"Новини\",\"mImageLink\":\"http://www.tneu.edu.ua/uploads/posts/2016-09/1474034804_img_5245.jpg\",\"mDescription\":\"14 вересня 2016 р. студенти та викладачі кафедри фінансів ім. С.І. Юрія презентували новостворений студентський науковий гурток „Financial education”/„Фінансова просвіта”, метою якого є залучення талановитої молоді до наукової діяльності та популяризації фінансових знань серед населення. Гурток покликаний стимулювати активну творчу роботу студентів у процесі навчання й оволодіння спеціальністю, виявляти серед них найбільш обдарованих, створювати умови для їх подальшого творчого зростання.\",\"mReadMoreLink\":\"http://www.tneu.edu.ua/news/9678-na-kafedri-finansiv-imeni-si-yuriia-rozpochav-robotu-novostvorenyi-studentskyi-naukovyi-gurtok-financial-education-finansova-prosvita.html\"},{\"mTitle\":\"Увага! Кредитна мобільність для професорсько-викладацького складу ТНЕУ у Жешівському університеті (Республіка Польща)!\",\"mDate\":\"2016-09-16T15:57:00.000Z\",\"mTopic\":\"Міжнародні програми\",\"mImageLink\":\"http://www.tneu.edu.ua/uploads/posts/2016-09/1474030713_8cda81fc7ad906927144235dda5fdf151428517078.jpg\",\"mDescription\":\"Шановні працівники Тернопільського національного економічного університету, відділ міжнародного співробітництва ТНЕУ оголошує конкурс для участі в академічній мобільності у Жешівському університеті по програмі Еразмус+.\",\"mReadMoreLink\":\"http://www.tneu.edu.ua/news/mizhnarodni-programy/9677-uvaga-kredytna-mobilnist-dlia-profesorsko-vykladackogo-skladu-tneu-u-zheshivskomu-universyteti-respublika-polscha.html\"},{\"mTitle\":\"До уваги студентів 1 курсу ННІМЕВ ім. Б.Д. Гаврилишина!\",\"mDate\":\"2016-09-16T09:30:00.000Z\",\"mTopic\":\"Новини / Наші ініціативи\",\"mImageLink\":\"http://www.tneu.edu.ua/uploads/posts/2016-09/1474014990_aval-1445246423_1444208244_fdfkmjjhg.png\",\"mDescription\":\"В понеділок, 19 вересня 2016 року з 14.00 до 16.00 год. відбудеться зустріч працівників «Райффайзен Банку Аваль» з студентами 1-го курсу ННІМЕВ ім. Б.Д. Гаврилишина з приводу видачі банківських карток. Зустріч буде проходити у читальному залі електронних ресурсів ТНЕУ (аудиторія 1209, 1-й корпус університету, вул. Львівська, 11, ). Для отримання картки при собі потрібно мати наступні документи: 1. паспорт (оригінал); 2. копію паспорта(сторінки 1,2 + всі сторінки, де є відмітки про прописку); 3. ідентифікаційний код; 4. копію ідентифікаційного коду. Присутність обов'язкова!\"},{\"mTitle\":\"Арт-зустріч «НЕформат»\",\"mDate\":\"2016-09-16T09:26:00.000Z\",\"mTopic\":\"Новини\",\"mImageLink\":\"http://www.tneu.edu.ua/uploads/posts/2016-09/1474007227_9wyl4ddx09w.jpg\",\"mDescription\":\"Вечір 15 вересня несподівано перетворив спортивний майданчик ТНЕУ на місце зустрічі талановитих та креативних студентів університету. Арт-зустріч «НЕформат», організована відділом гуманітарної освіти та виховання, уже вдруге довела, що такі мистецькі заходи під відкритим небом — чудовий спосіб самовираження та самореалізації творчо обдарованої молоді.\",\"mReadMoreLink\":\"http://www.tneu.edu.ua/news/9674-art-zustrich-neformat.html\"},{\"mTitle\":\"Знана випускниця ТНЕУ Т.Я. Слюз відвідала альма-матер\",\"mDate\":\"2016-09-15T16:05:00.000Z\",\"mTopic\":\"Новини\",\"mImageLink\":\"http://www.tneu.edu.ua/uploads/posts/2016-09/1473944754_267b8343.jpg\",\"mDescription\":\"15 вересня 2016 року до Тернопільського національного економічного університету завітала голова Державної казначейської служби України Тетяна Ярославівна Слюз.\",\"mReadMoreLink\":\"http://www.tneu.edu.ua/news/9673-znana-vypusknycia-tneu-tya-sliuz-vidvidala-alma-mater.html\"},{\"mTitle\":\"Екскурсія у бібліотеку ТНЕУ\",\"mDate\":\"2016-09-15T14:38:00.000Z\",\"mTopic\":\"Новини / Студентське життя\",\"mImageLink\":\"http://www.tneu.edu.ua/uploads/posts/2016-09/1473939515_0meyapygay4.jpg\",\"mDescription\":\"Життя студентської молоді нашого університету – це не тільки лекції, конференції, семінари, це спілкування, екскурсії, масові виховні заходи, незабутні враження від побаченого і почутого.\",\"mReadMoreLink\":\"http://www.tneu.edu.ua/news/9672-ekskursiia-u-biblioteku-tneu.html\"}]";
        ArrayList<News> news = new Gson().fromJson(br, new TypeToken<ArrayList<News>>() {
        }.getType());
        return news;
    }
    //endregion

//    private void registerBroadcastReceiver() {
//        mNewsViewerActivityBroadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                News news = new News( intent.getStringExtra("title"),
//                        intent.getStringExtra("date"),
//                        intent.getStringExtra("topic"),
//                        intent.getStringExtra("imageLink"),
//                        intent.getStringExtra("description"),
//                        intent.getStringExtra("readMoreLink"));
//                mNews.add(news);
//                mNewsRecyclerViewAdapter.notifyItemInserted(0);
//            }
//        };
//    }
}