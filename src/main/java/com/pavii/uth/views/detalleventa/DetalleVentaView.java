package com.pavii.uth.views.detalleventa;

import com.pavii.uth.components.phonenumberfield.PhoneNumberField;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Detalle Venta")
@Route("Detalle-Venta")
@Menu(order = 4, icon = "line-awesome/svg/sellcast.svg")
public class DetalleVentaView extends Composite<VerticalLayout> {

    public DetalleVentaView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        Hr hr = new Hr();
        FormLayout formLayout2Col = new FormLayout();
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        NumberField numberField = new NumberField();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();
        TextField textField5 = new TextField();
        TextField textField6 = new TextField();
        PhoneNumberField phoneNumber = new PhoneNumberField();
        Hr hr2 = new Hr();
        Hr hr3 = new Hr();
        DateTimePicker dateTimePicker = new DateTimePicker();
        ComboBox comboBox = new ComboBox();
        Hr hr4 = new Hr();
        Hr hr5 = new Hr();
        TextField textField7 = new TextField();
        TextField textField8 = new TextField();
        Hr hr6 = new Hr();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button buttonPrimary = new Button();
        Button buttonSecondary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        h3.setText("Detalle de Venta");
        h3.setWidth("100%");
        formLayout2Col.setWidth("100%");
        textField.setLabel("Codigo de Producto");
        textField2.setLabel("Nombre de Producto");
        numberField.setLabel("Cantidad de Producto");
        numberField.setWidth("min-content");
        textField3.setLabel("Laboratorio");
        textField3.setWidth("min-content");
        textField4.setLabel("Id de Cliente");
        textField4.setWidth("min-content");
        textField5.setLabel("Nombre de Cliente");
        textField5.setWidth("min-content");
        textField6.setLabel("Direccion");
        textField6.setWidth("min-content");
        phoneNumber.setLabel("Numero de Telefono");
        phoneNumber.setWidth("min-content");
        dateTimePicker.setLabel("Date time picker");
        dateTimePicker.setWidth("min-content");
        comboBox.setLabel("Forma de Pago");
        comboBox.setWidth("min-content");
        setComboBoxSampleData(comboBox);
        textField7.setLabel("Text field");
        textField7.setWidth("min-content");
        textField8.setLabel("Text field");
        textField8.setWidth("min-content");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        buttonPrimary.setText("Save");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSecondary.setText("Cancel");
        buttonSecondary.setWidth("min-content");
        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(hr);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(textField);
        formLayout2Col.add(textField2);
        formLayout2Col.add(numberField);
        formLayout2Col.add(textField3);
        formLayout2Col.add(textField4);
        formLayout2Col.add(textField5);
        formLayout2Col.add(textField6);
        formLayout2Col.add(phoneNumber);
        formLayout2Col.add(hr2);
        formLayout2Col.add(hr3);
        formLayout2Col.add(dateTimePicker);
        formLayout2Col.add(comboBox);
        formLayout2Col.add(hr4);
        formLayout2Col.add(hr5);
        formLayout2Col.add(textField7);
        formLayout2Col.add(textField8);
        layoutColumn2.add(hr6);
        layoutColumn2.add(layoutRow);
        layoutRow.add(buttonPrimary);
        layoutRow.add(buttonSecondary);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setComboBoxSampleData(ComboBox comboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }
}
