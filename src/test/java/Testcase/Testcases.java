package Testcase;

import org.testng.annotations.*;
import Pages.*;
import Utilities.BaseTest;

public class Testcases extends BaseTest {

    Loginpage loginpage;
    Changepasswordpage changepwd;
    Editprofilepage editprofile;
    F2FNonspokenpage f2fNonspoken;
    F2FSpokenpage f2fSpoken;
    Futurebookingpage futurebooking;
    Logoutpage logoutpage;
    Pastbookingpage pastbooking;
    Telephonebookingpage telephone;
    VideoNonspokenpage videoNonspoken;
    Videospokenpage videoSpoken;

    @BeforeMethod
    public void initPages() {
        // driver is inherited from BaseTest
        // test is ThreadLocal, accessed via getTest()
        loginpage      = new Loginpage(getDriver(), getTest());
        changepwd      = new Changepasswordpage(getDriver(), getTest());
        editprofile    = new Editprofilepage(getDriver(), getTest());
        f2fNonspoken   = new F2FNonspokenpage();
        f2fSpoken      = new F2FSpokenpage(getDriver(), getTest());
        futurebooking  = new Futurebookingpage(getDriver(), getTest());
        logoutpage     = new Logoutpage(getDriver(), getTest());
        pastbooking    = new Pastbookingpage(getDriver(), getTest());
        telephone      = new Telephonebookingpage(getDriver(), getTest());
        videoNonspoken = new VideoNonspokenpage(getDriver(), getTest());
        videoSpoken    = new Videospokenpage(getDriver(), getTest());
    }

    @Test(priority = 1)
    public void testLogin() {
        loginpage.loginToTLS("mirzamohsinlatif16@gmail.com", "Pakistan@1");
    }

    @Test(priority = 2)
    public void testChangePassword() {
        changepwd.changePassword("Pakistan@123");
    }

    @Test(priority = 3)
    public void testEditProfile() {
        editprofile.clickEditProfile();
    }

    @Test(priority = 4)
    public void testFutureBooking() {
        futurebooking.viewFutureBookings();
    }

    @Test(priority = 5)
    public void testPastBooking() {
        pastbooking.openPastBookings();
    }

    @Test(priority = 6)
    public void testF2FNonSpokenBooking() throws InterruptedException {
        f2fNonspoken.createBooking();
        f2fNonspoken.fillBookingDetails("Mohsin Latif", "03351180865", "QA", "Service User");
    }

    @Test(priority = 7)
    public void testF2FSpokenBooking() throws InterruptedException {
        f2fSpoken.createBooking();
        f2fSpoken.fillBookingDetails("Mohsin Latif", "03351180865", "QA", "Service User");
    }

    @Test(priority = 8)
    public void testTelephoneBooking() throws InterruptedException {
        telephone.createTelephoneSpokenBooking();
        telephone.fillBookingForm("Mohsin Latif", "03351180865", "QA", "Service User");
    }

    @Test(priority = 10)
    public void testVideoSpokenBooking() throws InterruptedException {
        videoSpoken.createBooking();
        videoSpoken.fillBookingDetails("Mohsin Latif", "03351180865", "QA", "Service User");
    }

    @Test(priority = 11)
    public void testLogout() {
        logoutpage.logout();
    }
}
