import{inputFieldProperties as i,labelProperties as p,helperTextProperties as s,errorMessageProperties as l}from "./vaadin-text-field-e82c445d.js";import{i as r,s as o,t as a}from "../vaadin-dev-tools.js";import"construct-style-sheets-polyfill";import"lit";import"lit/decorators.js";import"lit/directives/class-map.js";import"lit/static-html.js";const u={tagName:"vaadin-combo-box",displayName:"ComboBox",elements:[{selector:"vaadin-combo-box::part(input-field)",displayName:"Input field",properties:i},{selector:"vaadin-combo-box::part(toggle-button)",displayName:"Toggle button",properties:[r.iconColor,r.iconSize]},{selector:"vaadin-combo-box::part(label)",displayName:"Label",properties:p},{selector:"vaadin-combo-box::part(helper-text)",displayName:"Helper text",properties:s},{selector:"vaadin-combo-box::part(error-message)",displayName:"Error message",properties:l},{selector:"vaadin-combo-box-overlay::part(overlay)",displayName:"Overlay",properties:[o.backgroundColor,o.borderColor,o.borderWidth,o.borderRadius,o.padding]},{selector:"vaadin-combo-box-overlay vaadin-combo-box-item",displayName:"Overlay items",properties:[a.textColor,a.fontSize,a.fontWeight]},{selector:"vaadin-combo-box-overlay vaadin-combo-box-item::part(checkmark)::before",displayName:"Overlay item checkmark",properties:[r.iconColor,r.iconSize]}],async setupElement(e){e.overlayClass=e.getAttribute("class"),e.items=[{label:"Item",value:"value"}],e.value="value",e.opened=!0,await new Promise(t=>setTimeout(t,10))},async cleanupElement(e){e.opened=!1}};export{u as default};
