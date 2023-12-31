package com.android.wallpaper.module;

import android.app.WallpaperManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;

import com.android.wallpaper.model.CustomizationSectionController;
import com.android.wallpaper.model.CustomizationSectionController.CustomizationSectionNavigationController;
import com.android.wallpaper.model.PermissionRequester;
import com.android.wallpaper.model.WallpaperColorsViewModel;
import com.android.wallpaper.model.WallpaperPreviewNavigator;
import com.android.wallpaper.model.WallpaperSectionController;
import com.android.wallpaper.picker.customization.domain.interactor.WallpaperInteractor;
import com.android.wallpaper.picker.customization.ui.section.ScreenPreviewSectionController;
import com.android.wallpaper.picker.customization.ui.section.WallpaperQuickSwitchSectionController;
import com.android.wallpaper.picker.customization.ui.viewmodel.CustomizationPickerViewModel;
import com.android.wallpaper.util.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/** {@link CustomizationSections} for the wallpaper picker. */
public final class WallpaperPickerSections implements CustomizationSections {

    @Override
    public List<CustomizationSectionController<?>> getRevampedUISectionControllersForScreen(
            Screen screen,
            FragmentActivity activity,
            LifecycleOwner lifecycleOwner,
            WallpaperColorsViewModel wallpaperColorsViewModel,
            PermissionRequester permissionRequester,
            WallpaperPreviewNavigator wallpaperPreviewNavigator,
            CustomizationSectionNavigationController sectionNavigationController,
            @Nullable Bundle savedInstanceState,
            CurrentWallpaperInfoFactory wallpaperInfoFactory,
            DisplayUtils displayUtils,
            CustomizationPickerViewModel customizationPickerViewModel,
            WallpaperInteractor wallpaperInteractor,
            WallpaperManager wallpaperManager,
            boolean isTwoPaneAndSmallWidth) {
        List<CustomizationSectionController<?>> sectionControllers = new ArrayList<>();

        sectionControllers.add(
                new ScreenPreviewSectionController(
                        activity,
                        lifecycleOwner,
                        screen,
                        wallpaperInfoFactory,
                        wallpaperColorsViewModel,
                        displayUtils,
                        wallpaperPreviewNavigator,
                        wallpaperInteractor,
                        wallpaperManager,
                        isTwoPaneAndSmallWidth));
        sectionControllers.add(
                new WallpaperQuickSwitchSectionController(
                        customizationPickerViewModel.getWallpaperQuickSwitchViewModel(screen),
                        lifecycleOwner,
                        sectionNavigationController,
                        savedInstanceState == null));

        return sectionControllers;
    }

    @Override
    public List<CustomizationSectionController<?>> getAllSectionControllers(
            FragmentActivity activity,
            LifecycleOwner lifecycleOwner,
            WallpaperColorsViewModel wallpaperColorsViewModel,
            PermissionRequester permissionRequester,
            WallpaperPreviewNavigator wallpaperPreviewNavigator,
            CustomizationSectionNavigationController sectionNavigationController,
            @Nullable Bundle savedInstanceState,
            DisplayUtils displayUtils) {
        List<CustomizationSectionController<?>> sectionControllers = new ArrayList<>();

        sectionControllers.add(
                new WallpaperSectionController(
                        activity,
                        lifecycleOwner,
                        permissionRequester,
                        wallpaperColorsViewModel,
                        null,
                        sectionNavigationController,
                        wallpaperPreviewNavigator,
                        savedInstanceState,
                        displayUtils));

        return sectionControllers;
    }
}
