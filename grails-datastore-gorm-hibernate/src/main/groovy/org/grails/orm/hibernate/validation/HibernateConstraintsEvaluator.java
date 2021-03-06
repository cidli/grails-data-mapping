/* Copyright (C) 2011 SpringSource
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.grails.orm.hibernate.validation;

import java.util.Map;

import grails.core.GrailsDomainClassProperty;
import grails.validation.Constrained;
import grails.validation.ConstrainedProperty;
import org.grails.orm.hibernate.cfg.GrailsDomainBinder;
import org.grails.orm.hibernate.cfg.PropertyConfig;
import org.grails.validation.DefaultConstraintEvaluator;

/**
 * Extends default implementation to add Hibernate specific exceptions.
 *
 * @author Graeme Rocher
 * @since 2.0
 */
public class HibernateConstraintsEvaluator extends DefaultConstraintEvaluator {

    public HibernateConstraintsEvaluator(Map<String, Object> defaultConstraints) {
        super(defaultConstraints);
    }

    public HibernateConstraintsEvaluator() {
        // default
    }

    @Override
    protected void applyDefaultNullableConstraint(GrailsDomainClassProperty p, Constrained cp) {
        final PropertyConfig propertyConfig = new GrailsDomainBinder().getPropertyConfig(p);
        boolean insertable = propertyConfig != null ? propertyConfig.isInsertable() : true;

        if (!insertable) {
           cp.applyConstraint(ConstrainedProperty.NULLABLE_CONSTRAINT,true);
        }
        else {
            super.applyDefaultNullableConstraint(p, cp);
        }
    }
}
