package org.tg8.sdt.data.file;

import org.tg8.sdt.data.InternalID;

class FileIOCoordinatorID<E extends InternalID> extends BaseFileIOCoordinator<E> {

	private IDGenerator idGen;
	@Override
	protected E prepareObjectForSave(E obj) {
		if (obj.getInternalID() == null) {
			// for now, the convention is that the key is the class name
			obj.setInternalID(this.idGen.getId(obj.getClass().getName()));
		}
		return obj;
	}
	protected void setIdGen(IDGenerator idGen) {
		this.idGen = idGen;
	}
}
