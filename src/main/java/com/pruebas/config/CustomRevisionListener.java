package com.pruebas.config;

import org.hibernate.envers.RevisionListener;
import com.pruebas.model.auditoria.Revision;

public class CustomRevisionListener implements RevisionListener 
{
	
	@Override
	public void newRevision(Object revisionEntity) 
	{
		@SuppressWarnings("unused")
		final Revision revision = (Revision) revisionEntity;
	}

}
