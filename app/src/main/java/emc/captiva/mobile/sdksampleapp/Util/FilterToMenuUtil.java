package emc.captiva.mobile.sdksampleapp.Util;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import emc.captiva.mobile.sdksampleapp.Constant;
import emc.captiva.mobile.sdksampleapp.R;

/**
 * Created by Davix on 9/14/16.
 */

public class FilterToMenuUtil {

    public MenuItem getMenuFromFilterString(String item){

        int id = Constant.invalidId;
        switch (item){
            case "Black-White":
                id = R.id.ABBlackWhite;
                break;
            case "Gray":
                id = R.id.ABGray;
                break;
            case "Deskew":
                id = R.id.ABDeskew;
                break;
            case "Rotate Left":
                id = R.id.ABRotateLeft;
                break;
            case "Rotate Right":
                id = R.id.ABRotateRight;
                break;
            case "Crop":
                id = R.id.ABCrop;
                break;
            case "Quadrilateral Crop":
                id = R.id.ABQuadCrop;
                break;
            case "Auto Crop":
                id = R.id.ABAutoCrop;
                break;
            case "Lighter":
                id = R.id.ABLighter;
                break;
            case "Darker":
                id = R.id.ABDarker;
                break;
            case "Increase Contrast":
                id = R.id.ABIncreaseContrast;
                break;
            case "Decrease Contrast":
                id = R.id.ABDecreaseContrast;
                break;
            case "Remove Noise":
                id = R.id.ABRemoveNoise;
                break;
            case "Resize":
                id = R.id.ABResize;
                break;
            case "Rotate 180":
                id = R.id.ABRotate180;
                break;
        }
        if(id != Constant.invalidId){
            return createMenuItemFromID(id);
        }
        return null;
    }

    private MenuItem createMenuItemFromID(final int id){
        return new MenuItem() {
            @Override
            public int getItemId() {
                return id;
            }

            @Override
            public int getGroupId() {
                return 0;
            }

            @Override
            public int getOrder() {
                return 0;
            }

            @Override
            public MenuItem setTitle(CharSequence title) {
                return null;
            }

            @Override
            public MenuItem setTitle(int title) {
                return null;
            }

            @Override
            public CharSequence getTitle() {
                return null;
            }

            @Override
            public MenuItem setTitleCondensed(CharSequence title) {
                return null;
            }

            @Override
            public CharSequence getTitleCondensed() {
                return null;
            }

            @Override
            public MenuItem setIcon(Drawable icon) {
                return null;
            }

            @Override
            public MenuItem setIcon(int iconRes) {
                return null;
            }

            @Override
            public Drawable getIcon() {
                return null;
            }

            @Override
            public MenuItem setIntent(Intent intent) {
                return null;
            }

            @Override
            public Intent getIntent() {
                return null;
            }

            @Override
            public MenuItem setShortcut(char numericChar, char alphaChar) {
                return null;
            }

            @Override
            public MenuItem setNumericShortcut(char numericChar) {
                return null;
            }

            @Override
            public char getNumericShortcut() {
                return 0;
            }

            @Override
            public MenuItem setAlphabeticShortcut(char alphaChar) {
                return null;
            }

            @Override
            public char getAlphabeticShortcut() {
                return 0;
            }

            @Override
            public MenuItem setCheckable(boolean checkable) {
                return null;
            }

            @Override
            public boolean isCheckable() {
                return false;
            }

            @Override
            public MenuItem setChecked(boolean checked) {
                return null;
            }

            @Override
            public boolean isChecked() {
                return false;
            }

            @Override
            public MenuItem setVisible(boolean visible) {
                return null;
            }

            @Override
            public boolean isVisible() {
                return false;
            }

            @Override
            public MenuItem setEnabled(boolean enabled) {
                return null;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public boolean hasSubMenu() {
                return false;
            }

            @Override
            public SubMenu getSubMenu() {
                return null;
            }

            @Override
            public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener menuItemClickListener) {
                return null;
            }

            @Override
            public ContextMenu.ContextMenuInfo getMenuInfo() {
                return null;
            }

            @Override
            public void setShowAsAction(int actionEnum) {

            }

            @Override
            public MenuItem setShowAsActionFlags(int actionEnum) {
                return null;
            }

            @Override
            public MenuItem setActionView(View view) {
                return null;
            }

            @Override
            public MenuItem setActionView(int resId) {
                return null;
            }

            @Override
            public View getActionView() {
                return null;
            }

            @Override
            public MenuItem setActionProvider(ActionProvider actionProvider) {
                return null;
            }

            @Override
            public ActionProvider getActionProvider() {
                return null;
            }

            @Override
            public boolean expandActionView() {
                return false;
            }

            @Override
            public boolean collapseActionView() {
                return false;
            }

            @Override
            public boolean isActionViewExpanded() {
                return false;
            }

            @Override
            public MenuItem setOnActionExpandListener(OnActionExpandListener listener) {
                return null;
            }
        };
    }

}
