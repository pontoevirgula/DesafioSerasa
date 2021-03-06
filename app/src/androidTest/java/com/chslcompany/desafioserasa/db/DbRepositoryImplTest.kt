package com.chslcompany.desafioserasa.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.chslcompany.desafioserasa.data.db.datasource.CharactersDao
import com.chslcompany.desafioserasa.data.db.datasource.CharactersDataBase
import com.chslcompany.desafioserasa.domain.model.Results
import com.chslcompany.desafioserasa.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class DbRepositoryImplTest : TestCase(){
    private lateinit var dataBase: CharactersDataBase
    private lateinit var dao: CharactersDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CharactersDataBase::class.java).allowMainThreadQueries()
            .build()

        dao = dataBase.charactersDao()
    }

    @After
    fun closeDb() {
        dataBase.close()
    }

    @Test
    fun insertCharacter() = runBlockingTest {
        val result = mockResult()

        dao.insertCharacter(result)
        val getResults = dao.getAllCharacters().getOrAwaitValue()

        assertThat(getResults.contains(result))
    }

    @Test
    fun deleteCharacters() = runBlockingTest {
        val result = mockResult()

        dao.insertCharacter(result)
        dao.deleteCharacter(result)

        val getResults = dao.getAllCharacters().getOrAwaitValue()

        assertThat(getResults).doesNotContain(result)
    }

    private fun mockResult(): Results =
        Gson().fromJson("{\n" +
                "  \"comics\": {\n" +
                "    \"available\": 4,\n" +
                "    \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1017100/comics\",\n" +
                "    \"items\": [\n" +
                "      {\n" +
                "        \"name\": \"FREE COMIC BOOK DAY 2013 1 (2013) #1\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/47176\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Hulk (2008) #53\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/40632\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Hulk (2008) #54\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/40630\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Hulk (2008) #55\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/40628\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"returned\": 4\n" +
                "  },\n" +
                "  \"description\": \"Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! \",\n" +
                "  \"events\": {\n" +
                "    \"available\": 0,\n" +
                "    \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1017100/events\",\n" +
                "    \"items\": [],\n" +
                "    \"returned\": 0\n" +
                "  },\n" +
                "  \"id\": 1017100,\n" +
                "  \"modified\": \"2013-09-18T15:54:04-0400\",\n" +
                "  \"name\": \"A-Bomb (HAS)\",\n" +
                "  \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1017100\",\n" +
                "  \"series\": {\n" +
                "    \"available\": 2,\n" +
                "    \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1017100/series\",\n" +
                "    \"items\": [\n" +
                "      {\n" +
                "        \"name\": \"FREE COMIC BOOK DAY 2013 1 (2013)\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/series/17765\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Hulk (2008 - 2012)\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/series/3374\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"returned\": 2\n" +
                "  },\n" +
                "  \"stories\": {\n" +
                "    \"available\": 7,\n" +
                "    \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1017100/stories\",\n" +
                "    \"items\": [\n" +
                "      {\n" +
                "        \"name\": \"Hulk (2008) #55\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92078\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Interior #92079\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92079\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Hulk (2008) #54\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92082\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Interior #92083\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92083\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Hulk (2008) #53\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92086\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Interior #92087\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92087\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"cover from Free Comic Book Day 2013 (Avengers/Hulk) (2013) #1\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/105929\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"returned\": 7\n" +
                "  },\n" +
                "  \"thumbnail\": {\n" +
                "    \"extension\": \"jpg\",\n" +
                "    \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16\"\n" +
                "  },\n" +
                "  \"urls\": [\n" +
                "    {\n" +
                "      \"type\": \"detail\",\n" +
                "      \"url\": \"http://marvel.com/characters/76/a-bomb?utm_campaign=apiRef&utm_source=de34757044858291b4dd5ddd470ddb83\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"comiclink\",\n" +
                "      \"url\": \"http://marvel.com/comics/characters/1017100/a-bomb_has?utm_campaign=apiRef&utm_source=de34757044858291b4dd5ddd470ddb83\"\n" +
                "    }\n" +
                "  ]\n" +
                "}", Results::class.java)
}