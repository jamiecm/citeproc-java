// Copyright 2013-2017 Michel Kraemer
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package de.undercouch.citeproc.lint;

/**
 * An event produced by {@link Linter} related to a specific
 * citation item
 * @author Michel Kraemer
 * @since 1.1.0
 */
public interface LintItemEvent extends LintEvent {
	/**
	 * Get the ID of the citation item this event is related to
	 * @return the citation ID
	 */
	String getCitationId();
}
