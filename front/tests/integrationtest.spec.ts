import { test, expect } from "@playwright/test";

/**
  The general shapes of tests in Playwright Test are:
    1. Navigate to a URL
    2. Interact with the page
    3. Assert something about the page against your expectations
  Look for this pattern in the tests below!
 */

// If you needed to do something before every test case
test.beforeEach(async ({ page }) => {
  await page.goto("http://localhost:5173/");
});

test("on page load, i see an input bar", async ({ page }) => {
  await expect(page.getByText("This is our app!")).toBeVisible();
  await expect(page.getByText("This is not our app!")).toHaveCount(0);
});
