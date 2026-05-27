package SMM.projekt

import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationFlowTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun userCanNavigateThroughApplication() {

        composeRule
            .onNodeWithText("Witaj w MyDesk")
            .assertExists()

        composeRule
            .onNodeWithTag("category_1")
            .performClick()

        composeRule.waitForIdle()

        composeRule
            .onAllNodes(hasClickAction())[0]
            .performClick()

        composeRule.waitForIdle()

        composeRule
            .onNodeWithText("Wróć do listy")
            .assertExists()

        composeRule
            .onNodeWithContentDescription("Wróć")
            .performClick()

        composeRule.waitForIdle()

        composeRule
            .onNodeWithContentDescription("Wróć")
            .performClick()

        composeRule.waitForIdle()

        composeRule
            .onNodeWithText("Witaj w MyDesk")
            .assertExists()
    }
}