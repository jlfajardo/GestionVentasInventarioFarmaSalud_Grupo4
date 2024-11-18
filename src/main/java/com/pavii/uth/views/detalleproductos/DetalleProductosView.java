package com.pavii.uth.views.detalleproductos;

import com.pavii.uth.controller.ClientesInteractor;
import com.pavii.uth.controller.ClientesInteractorImpl;
import com.pavii.uth.controller.ProductosInteractor;
import com.pavii.uth.controller.ProductosInteractorImpl;
import com.pavii.uth.data.Clientes;
import com.pavii.uth.data.Productos;
import com.pavii.uth.views.detalleclientes.DetalleClientesView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.data.binder.BeanValidationBinder;
//import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@PageTitle("Detalle Productos")
@Route("Detalle-Productos/:idproducto?/:action?(edit)")
@Menu(order = 3, icon = "line-awesome/svg/product-hunt.svg")
public class DetalleProductosView extends Div implements BeforeEnterObserver, DetalleProductosViewModel {

    private final String PRODUCTOS_ID = "idproducto";
    private final String PRODUCTOS_EDIT_ROUTE_TEMPLATE = "Detalle-Productos/%s/edit";

    private final Grid<Productos> grid = new Grid<>(Productos.class, false);

    //private TextField idProducto;
    private TextField nombreProducto;
    private TextField descripcionProducto;
    private TextField precioProducto;
    private TextField cantidadDisponible;
    private TextField categoria;
    private TextField laboratorio;

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

    //private final BeanValidationBinder<Productos> binder;

    private Productos productos;
    private List<Productos> listaProductos;
    private ProductosInteractor controlador;

  

    public DetalleProductosView() {
        addClassNames("detalle-productos-view");
        
        controlador = new ProductosInteractorImpl(this); 
        listaProductos = new ArrayList<>();

        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        //grid.addColumn("idProducto").setAutoWidth(true);
        grid.addColumn("nombre").setAutoWidth(true);
        grid.addColumn("descripcion").setAutoWidth(true);
        grid.addColumn("precio").setAutoWidth(true);
        grid.addColumn("stock").setAutoWidth(true);
        grid.addColumn("categoria").setAutoWidth(true);
        grid.addColumn("laboratorio").setAutoWidth(true);
        
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(PRODUCTOS_EDIT_ROUTE_TEMPLATE, event.getValue().getidproducto()));
            } else {
                clearForm();
                UI.getCurrent().navigate(DetalleProductosView.class);
            }
        });

        /*Configure Form
        binder = new BeanValidationBinder<>(Productos.class);

        // Bind fields. This is where you'd define e.g. validation rules
        binder.forField(idProducto).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("idProducto");
        binder.forField(precioProducto).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("precioProducto");
        binder.forField(cantidadDisponible).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("cantidadDisponible");

        binder.bindInstanceFields(this);*/

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.productos == null) {
                    this.productos = new Productos();
                }
                //binder.writeBean(this.productos);
              
                clearForm();
                refreshGrid();
                Notification.show("El producto ha sido guardado de manera exitosa");
                UI.getCurrent().navigate(DetalleProductosView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error guardando el producto");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } /*catch (ValidationException validationException) {
                Notification.show("Failed to update the data. Check again that all values are valid");
            }*/
        });
        
        controlador.consultarProductos();
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> productosId = event.getRouteParameters().get(PRODUCTOS_ID).map(Long::parseLong);
        if (productosId.isPresent()) {
        	Productos productoSeleccionado = obtenerProducto(productosId.get());
        	if(productoSeleccionado == null) {
        		mostrarMensajeError("El alumno con el id "+productosId.get()+" no existe");
        		refreshGrid(); 
        		event.forwardTo(DetalleClientesView.class);
            	} else {
            	populateForm(productoSeleccionado);
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
        //idProducto = new TextField("Id Producto");
        nombreProducto = new TextField("Nombre Producto");
        nombreProducto.setPrefixComponent(VaadinIcon.PILLS.create());
        
        descripcionProducto = new TextField("Descripcion Producto");
        descripcionProducto.setPrefixComponent(VaadinIcon.PILL.create());
        
        precioProducto = new TextField("Precio Producto");
        precioProducto.setPrefixComponent(VaadinIcon.DOLLAR.create());
        
        cantidadDisponible = new TextField("Cantidad Disponible");
        cantidadDisponible.setPrefixComponent(VaadinIcon.EDIT.create());
        
        categoria = new TextField("Categoria");
        categoria.setPrefixComponent(VaadinIcon.GROUP.create());
        
        laboratorio = new TextField("Laboratorio");
        laboratorio.setPrefixComponent(VaadinIcon.PLUS_CIRCLE_O.create());
        formLayout.add(nombreProducto, descripcionProducto, precioProducto, cantidadDisponible, categoria,
                laboratorio);

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }
    
    private Productos obtenerProducto(Long id) {
		Productos encotrado = null;
		for(Productos al: listaProductos) {
			if(al.getidproducto() == id) {
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
        this.controlador.consultarProductos();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Productos value) {
        this.productos = value;
        if(value == null) {
        	nombreProducto.setValue("");
        	descripcionProducto.setValue("");
        	precioProducto.setValue("");
        	cantidadDisponible.setValue("");
        	categoria.setValue("");
        	laboratorio.setValue("");
        }else {
        	nombreProducto.setValue(value.getnombre());
        	descripcionProducto.setValue(value.getdescripcion());
        	precioProducto.setValue(value.getprecio().toString());
        	cantidadDisponible.setValue(value.getstock().toString());
        	categoria.setValue(value.getcategoria());
        	laboratorio.setValue(value.getdescripcion());
        }
        //binder.readBean(this.productos);

    }
    
    @Override
	public void mostrarProductosEnGrid(List<Productos> items) {
		Collection<Productos> itemsCollection = items;
		this.listaProductos = items;
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
