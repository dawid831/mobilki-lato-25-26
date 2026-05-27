package SMM.projekt

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun homeScreen_displaysMainElements() {
        composeRule
            .onNodeWithText("Witaj w MyDesk")
            .assertExists()

        composeRule
            .onNodeWithText("Wybierz sprzęt do podglądu")
            .assertExists()
    }

    @Test
    fun canOpenSettingsScreen() {
        composeRule
            .onNodeWithText("Witaj w MyDesk")
            .assertExists()

        composeRule
            .onNodeWithContentDescription("Ustawienia")
            .performClick()

        composeRule
            .onNodeWithText("Ustawienia")
            .assertExists()
    }

    @Test
    fun homeScreen_worksInLandscape() {
        composeRule.activity.requestedOrientation =
            android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        composeRule.waitForIdle()

        composeRule
            .onNodeWithText("Witaj w MyDesk")
            .assertExists()
    }

    @Test
    fun homeScreen_worksInPortrait() {
        composeRule.activity.requestedOrientation =
            android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        composeRule.waitForIdle()

        composeRule
            .onNodeWithText("Witaj w MyDesk")
            .assertExists()
    }
}