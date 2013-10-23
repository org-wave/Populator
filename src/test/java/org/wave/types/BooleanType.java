/*
   Copyright 2013 Benedito Barbosa Ribeiro Neto/Christian Linhares Peixoto/Mauricio da Silva Marinho

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.wave.types;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

public class BooleanType {

	private boolean standard;

	@AssertTrue
	private boolean assertTrue;

	@AssertFalse
	private boolean assertFalse;

	public boolean isStandard() {
		return this.standard;
	}

	public boolean isAssertTrue() {
		return this.assertTrue;
	}

	public boolean isAssertFalse() {
		return this.assertFalse;
	}

}
