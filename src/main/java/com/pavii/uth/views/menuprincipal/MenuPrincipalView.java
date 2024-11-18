package com.pavii.uth.views.menuprincipal;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("Menu Principal")
@Route("Menu-Principal")
@Menu(order = 1, icon = "line-awesome/svg/clinic-medical-solid.svg")
public class MenuPrincipalView extends Composite<VerticalLayout> {

    public MenuPrincipalView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        Hr hr = new Hr();
        Icon icon = new Icon();
        Button buttonPrimary = new Button();
        Icon icon2 = new Icon();
        Button buttonPrimary2 = new Button();
        Icon icon3 = new Icon();
        Button buttonPrimary3 = new Button();
        Hr hr2 = new Hr();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        Button buttonPrimary4 = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutColumn2.setHeightFull();
        layoutRow.setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setJustifyContentMode(JustifyContentMode.CENTER);
        layoutColumn2.setAlignItems(Alignment.CENTER);
        layoutRow3.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow3);
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.setHeight("30px");
        icon.setIcon(VaadinIcon.USER);
      //  icon.setWidth("50px");
       // icon.setHeight("50px");
        buttonPrimary.setText("Clientes");
        buttonPrimary.setWidth("500px");
        buttonPrimary.setHeight("50px");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        icon2.setIcon("lumo:user");
        icon2 = new Icon(VaadinIcon.DOCTOR_BRIEFCASE);
       // icon2.setWidth("50px");
      //  icon2.setHeight("50px");
        buttonPrimary2.setText("Producto");
        buttonPrimary2.setWidth("500px");
        buttonPrimary2.setHeight("50px");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        icon3.setIcon("lumo:user");
        icon3 = new Icon(VaadinIcon.CASH);
      //  icon3.setWidth("50px");
      //  icon3.setHeight("50px");
        buttonPrimary3.setText("Facturacion");
        buttonPrimary3.setWidth("500px");
        buttonPrimary3.setHeight("50px");
        buttonPrimary3.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutRow2.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        layoutRow2.setAlignItems(Alignment.CENTER);
        layoutRow2.setJustifyContentMode(JustifyContentMode.END);
        buttonPrimary4.setText("Salir");
        buttonPrimary4.setWidth("min-content");
        buttonPrimary4.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(layoutRow);
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(layoutRow3);
        layoutColumn2.add(hr);
        layoutColumn2.add(icon);
        layoutColumn2.add(buttonPrimary);
        layoutColumn2.add(icon2);
        layoutColumn2.add(buttonPrimary2);
        layoutColumn2.add(icon3);
        layoutColumn2.add(buttonPrimary3);
        layoutColumn2.add(hr2);
        layoutColumn2.add(layoutRow2);
        layoutRow2.add(buttonPrimary4);
    }
}
