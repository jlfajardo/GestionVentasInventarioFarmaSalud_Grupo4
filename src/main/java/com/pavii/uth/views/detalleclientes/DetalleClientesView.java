package com.pavii.uth.views.detalleclientes;

import com.pavii.uth.controller.ClientesInteractor;
import com.pavii.uth.controller.ClientesInteractorImpl;
import com.pavii.uth.data.Clientes;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.Optional;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import java.util.Collection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;


@PageTitle("Detalle Clientes")
@Route("Detalle-Clientes/:idcliente?/:action?(edit)")
@Menu(order = 2, icon = "line-awesome/svg/person-booth-solid.svg")
public class DetalleClientesView extends Div implements BeforeEnterObserver, DetalleClientesViewModel {

    private final String CLIENTES_ID = "idcliente";
    private final String CLIENTES_EDIT_ROUTE_TEMPLATE = "Detalle-Clientes/%s/edit";

    private final Grid<Clientes> grid = new Grid<>(Clientes.class, false);

    private TextField nombreCliente;
    private TextField telefonoCliente;
    private TextField emailCliente;
    private TextField direccionCliente;
    private DatePicker fechaNacimientoCliente;

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

   // private final BeanValidationBinder<Clientes> binder;
 
    private Clientes clientes;
    private List<Clientes> listaClientes;
    private ClientesInteractor controlador;



    public DetalleClientesView() {

        addClassNames("detalle-clientes-view");
        
        controlador = new ClientesInteractorImpl(this); 
        listaClientes = new ArrayList<>();
        

        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        //grid.addColumn("idcliente").setAutoWidth(true);
        grid.addColumn("nombre").setAutoWidth(true).setHeader("Nombre");
        grid.addColumn("telefono").setAutoWidth(true).setHeader("Telefono");
        grid.addColumn("correo").setAutoWidth(true).setHeader("Correo");
        grid.addColumn("direccion").setAutoWidth(true).setHeader("Direccion");
        grid.addColumn("fecha_nacimiento").setAutoWidth(true).setHeader("Fecha de Nacimiento");
       
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(CLIENTES_EDIT_ROUTE_TEMPLATE, event.getValue().getidcliente()));
            } else {
                clearForm();
                UI.getCurrent().navigate(DetalleClientesView.class);
            }
        });

        // Configure Form
       // binder = new BeanValidationBinder<>(Clientes.class);

        // Bind fields. This is where you'd define e.g. validation rules
       // binder.forField(idCliente).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
          //      .bind("idCliente");
      //  binder.forField(telefonoCliente).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
             //   .bind("telefonoCliente");

     //   binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.clientes == null) {
                    this.clientes = new Clientes();
                }
                //binder.writeBean(this.clientes);
         
                clearForm();
                refreshGrid();
                Notification.show("El cliente ha sido guardado de manera exitosa");
                UI.getCurrent().navigate(DetalleClientesView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error guardando el cliente");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } 
        });
        controlador.consultarClientes();
        
        
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> clientesId = event.getRouteParameters().get(CLIENTES_ID).map(Long::parseLong);
        if (clientesId.isPresent()) {
        	Clientes clienteSeleccionado = obtenerCliente(clientesId.get());
        	if(clienteSeleccionado == null) {
        		mostrarMensajeError("El alumno con el id "+clientesId.get()+" no existe");
        		refreshGrid(); 
        		event.forwardTo(DetalleClientesView.class);
            	} else {
            	populateForm(clienteSeleccionado);
            }
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        //idCliente = new TextField("Id Cliente");
        nombreCliente = new TextField("Nombre Cliente");
        nombreCliente.setPrefixComponent(VaadinIcon.CLIPBOARD_USER.create());

        
        telefonoCliente = new TextField("Telefono Cliente");
        telefonoCliente.setHelperText("Formato: +(504)9988-7766");
        telefonoCliente.setPrefixComponent(VaadinIcon.PHONE.create());
        
        emailCliente = new TextField("Email Cliente");
        emailCliente.setPrefixComponent(VaadinIcon.MAILBOX.create());
        
        direccionCliente = new TextField("Direccion Cliente");
        direccionCliente.setPrefixComponent(VaadinIcon.HOME_O.create());
        
        fechaNacimientoCliente = new DatePicker("Fecha Nacimineto Cliente");
        LocalDate now = LocalDate.now(ZoneId.systemDefault());
        fechaNacimientoCliente.setMin(now.plusYears(-50));
        fechaNacimientoCliente.setMax(now.plusDays(-60));
        
        formLayout.add(nombreCliente, telefonoCliente, emailCliente, direccionCliente,
        		fechaNacimientoCliente);

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
                ButtonVariant.LUMO_ERROR);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }
    
    private Clientes obtenerCliente(Long id) {
		Clientes encotrado = null;
		for(Clientes al: listaClientes) {
			if(al.getidcliente() == id) {
				encotrado = al;
				break;
			}
		}
		return encotrado;
	}

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
        this.controlador.consultarClientes();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Clientes value) {
        this.clientes = value;
        if(value == null) {
        	nombreCliente.setValue("");
        	telefonoCliente.setValue("");
        	emailCliente.setValue("");
        	direccionCliente.setValue("");
        	fechaNacimientoCliente.clear();
        }else {
        	nombreCliente.setValue(value.getnombre());
        	telefonoCliente.setValue(value.gettelefono().toString());
        	emailCliente.setValue(value.getcorreo());
        	direccionCliente.setValue(value.getdireccion());
            LocalDate fechaNac = value.getfecha_nacimiento().toLocalDate();
            fechaNacimientoCliente.setValue(fechaNac);
        }
      //  binder.readBean(this.clientes);

    }
    
    @Override
	public void mostrarClientesEnGrid(List<Clientes> items) {
		Collection<Clientes> itemsCollection = items;
		this.listaClientes = items;
		grid.setItems(itemsCollection);
	}

	@Override
	public void mostrarMensajeError(String mensaje) {
		Notification notification = new Notification();
		notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

		Div text = new Div(new Text(mensaje));

		Button closeButton = new Button(new Icon("lumo", "cross"));
		closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
		closeButton.setAriaLabel("Close");
		closeButton.addClickListener(event -> {
		    notification.close();
		});

		HorizontalLayout layout = new HorizontalLayout(text, closeButton);
		layout.setAlignItems(Alignment.CENTER);

		notification.add(layout);
		notification.open();
	}

	@Override
	public void mostrarMensajeExito(String mensaje) {
		Notification notification = Notification.show(mensaje);
		notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
		notification.open();
	}
}
