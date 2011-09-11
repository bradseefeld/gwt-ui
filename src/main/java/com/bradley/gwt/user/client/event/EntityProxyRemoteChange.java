package com.bradley.gwt.user.client.event;

import com.bradley.gwt.user.client.event.EntityProxyRemoteChange.Handler;
import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.requestfactory.shared.EntityProxy;

/**
 * Fired when an EntityProxy changes on the remote server.
 * 
 * @author bseefeld
 *
 */
public class EntityProxyRemoteChange<T extends EntityProxy> extends Event<Handler<T>> {

	public interface Handler<T extends EntityProxy> {
		
		void onRemoteChange(EntityProxyRemoteChange<T> event);
	}
	
	public enum WriteOperation {
		SAVE,
		DELETE
	}
	
	protected T changedProxy;
	
	protected WriteOperation writeOperation;
	
	private static final Type<EntityProxyRemoteChange.Handler<?>> TYPE =
	      new Type<EntityProxyRemoteChange.Handler<?>>();
	
	public EntityProxyRemoteChange(T changedProxy, WriteOperation writeOperation) {
		this.changedProxy = changedProxy;
		this.writeOperation = writeOperation;
	}
	
	public T getChangedEntityProxy() {
		return changedProxy;
	}
	
	public WriteOperation getWriteOperation() {
		return writeOperation;
	}
	
	public static <T extends EntityProxy> HandlerRegistration registerForProxyType(EventBus eventBus, Class<T> proxyType, EntityProxyRemoteChange.Handler<T> handler) {
		return eventBus.addHandlerToSource(TYPE, proxyType, handler);
	}
	
	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public Event.Type<Handler<T>> getAssociatedType() {
		return (Type) TYPE;
	}

	@Override
	protected void dispatch(Handler<T> handler) {
		handler.onRemoteChange(this);
	}
}
